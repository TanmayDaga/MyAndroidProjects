package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;

import android.view.MenuItem;
import android.view.View;

import com.example.news.database.AppDatabase;
import com.example.news.databinding.ActivityMainBinding;
import com.facebook.drawee.backends.pipeline.Fresco;


import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity{

    public static final String LAST_UPDATED_DATA = "last_updated_data";
    public static final String ALREADY_HAVE_DATA_BUT_REFRESH = "already";
    public static final String NEVER_BEEN_INITIATED = "never";
    public static final String NOT_REQUIRED_TO_REFRESH = "not";
    private NewsAdapter mAdapter;
    private SharedPreferences sharedPreferences;
    private ActivityMainBinding mBinding;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);



        sharedPreferences = this.getSharedPreferences("preference",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        mAdapter = new NewsAdapter(this);
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));




        FetchData.getAndSetData(mAdapter,this,MainActivity.this,Utilities.checkToRefresh(this),"");


//
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                mBinding.editQuery.setVisibility(View.VISIBLE);
                return true;
            default:
                return false;


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.getCacheDir().delete();
    }
}