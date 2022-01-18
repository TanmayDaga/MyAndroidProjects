package com.example.backgroundsetter

import android.R.attr
import android.app.WallpaperManager
import android.content.Context
import android.graphics.*

import android.util.Log

import androidx.work.Worker
import androidx.work.WorkerParameters

import java.lang.Exception
import java.net.URL
import android.graphics.BitmapFactory
import android.R.attr.path
import android.content.ContentResolver
import android.content.ContentValues

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore

import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.*
import java.io.File.separator


class changeBg(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    val TAG = applicationContext.javaClass.simpleName


    override fun doWork(): Result {
        return if (changeBackground(applicationContext, getImage(applicationContext))) Result.success()
        else Result.failure()
    }


}

fun changeBackground(context: Context,icon:Bitmap): Boolean {


    icon?.let {
        runBlocking {
            launch {
                saveImage(icon, context.applicationContext, "Wallpapers")
            }
        }
        var wallpaperManager: WallpaperManager =
            WallpaperManager.getInstance(context.applicationContext)
        wallpaperManager.setBitmap(icon)

        wallpaperManager.suggestDesiredDimensions(
            (wallpaperManager.desiredMinimumWidth) / 100,
            wallpaperManager.desiredMinimumHeight
        )
        return true
    }
    return false
}
fun getImage(context: Context):Bitmap{
    val url: String = "https://source.unsplash.com/random/?motivational%20quotes"
    lateinit var icon: Bitmap
    try {

        Log.d(context.applicationContext.javaClass.simpleName, "fetching image")
        var inputStr: InputStream = URL(url).openStream()

        icon = BitmapFactory.decodeStream(inputStr)

    } catch (e: Exception) {
        e.printStackTrace()
        Log.d(context.javaClass.simpleName, "can't set background")

    }
    return icon
}


fun saveImage(bitmap: Bitmap, context: Context, folderName: String) {
    if (android.os.Build.VERSION.SDK_INT >= 29) {
        val values = contentValues()
        values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + folderName)
        values.put(MediaStore.Images.Media.IS_PENDING, true)
        // RELATIVE_PATH and IS_PENDING are introduced in API 29.

        val uri: Uri? =
            context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        if (uri != null) {
            saveImageToStream(bitmap, context.contentResolver.openOutputStream(uri))
            values.put(MediaStore.Images.Media.IS_PENDING, false)
            context.contentResolver.update(uri, values, null, null)
        }
    } else {
        val directory =
            File(Environment.getExternalStorageDirectory().toString() + separator + folderName)
        // getExternalStorageDirectory is deprecated in API 29

        if (!directory.exists()) {
            directory.mkdirs()
        }
        val fileName = System.currentTimeMillis().toString() + ".png"
        val file = File(directory, fileName)
        saveImageToStream(bitmap, FileOutputStream(file))
        if (file.absolutePath != null) {
            val values = contentValues()
            values.put(MediaStore.Images.Media.DATA, file.absolutePath)
            // .DATA is deprecated in API 29
            context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        }
    }
}

private fun contentValues(): ContentValues {
    val values = ContentValues()
    values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
    values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
    values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
    return values
}

private fun saveImageToStream(bitmap: Bitmap, outputStream: OutputStream?) {
    if (outputStream != null) {
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
