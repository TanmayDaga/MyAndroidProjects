package com.example.backgroundsetter

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import androidx.work.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Time
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val ONCE_DONE_PREFERENCE_KEY:String = "once"


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shrd:SharedPreferences = this.getSharedPreferences("demo", MODE_PRIVATE)






        if(!shrd.contains(ONCE_DONE_PREFERENCE_KEY)){
            val editor:SharedPreferences.Editor = shrd.edit()
            editor.putString(ONCE_DONE_PREFERENCE_KEY,"true")

            val imageRequest: WorkRequest = PeriodicWorkRequestBuilder<changeBg>(1,TimeUnit.MINUTES).
            setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

            ).build()
            WorkManager.getInstance(this).enqueue(imageRequest)

            val button:Button = findViewById(R.id.button)
            val imageView:ImageView = findViewById(R.id.imageView)
            button.setOnContextClickListener {
                var block = runBlocking (Dispatchers.IO){

                    launch {
                        val img = getImage(this@MainActivity)
                        imageView.setImageBitmap(img)
                        changeBackground(this@MainActivity, getImage(this@MainActivity))
                    }
                }


                return@setOnContextClickListener true
            }
        }


    }


}