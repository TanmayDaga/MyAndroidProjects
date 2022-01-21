package com.example.news;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utilities {


    public static Date convertDate(String date) throws ParseException {
        DateFormat dateFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            2022-01-19T13:16:55Z
            dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        }
        Date date2 = dateFormat.parse(date);//You will get date object relative to server/client timezone wherever it is parsed
        return date2;
    }



    public static String checkToRefresh(Context context){

        SharedPreferences sharedPreferences  = context.getSharedPreferences(context.getString(R.string.preference_key),Context.MODE_PRIVATE);
        if (sharedPreferences.contains(MainActivity.LAST_UPDATED_DATA)){
            Log.d("Utilities",sharedPreferences.getString(MainActivity.LAST_UPDATED_DATA,""));
            String lastUpdateAtString = sharedPreferences.getString(MainActivity.LAST_UPDATED_DATA,"");
            if(System.currentTimeMillis()-Long.parseLong(lastUpdateAtString)> TimeUnit.DAYS.toMillis(1)&&!Utilities.isNetworkAvailable(context)){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(MainActivity.LAST_UPDATED_DATA,String.valueOf(System.currentTimeMillis()));
                editor.apply();
                return MainActivity.ALREADY_HAVE_DATA_BUT_REFRESH;
            }
            return MainActivity.NOT_REQUIRED_TO_REFRESH;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MainActivity.LAST_UPDATED_DATA,String.valueOf(System.currentTimeMillis()));
        editor.apply();
        return MainActivity.NEVER_BEEN_INITIATED;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void setEmptyLayout(RecyclerView recyclerView, TextView textView){
        recyclerView.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
    }

    public static void setFullLayout(RecyclerView recyclerView,TextView textView){
        recyclerView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
    }
}
