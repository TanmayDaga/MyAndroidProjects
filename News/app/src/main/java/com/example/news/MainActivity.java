package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.database.AppDatabase;
import com.example.news.databinding.ActivityMainBinding;
import com.facebook.drawee.backends.pipeline.Fresco;


import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnNewsListener {

    public static final String LAST_UPDATED_DATA = "last_updated_data";
    public static final String ALREADY_HAVE_DATA_BUT_REFRESH = "already";
    public static final String NEVER_BEEN_INITIATED = "never";
    public static final String NOT_REQUIRED_TO_REFRESH = "not";

    private ActivityMainBinding mBinding;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        NewsAdapter mAdapter = new NewsAdapter(this,this,
                mBinding.recyclerView,
                mBinding.emptyTextView
                );
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));



        mBinding.editQuery.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);
        mBinding.editQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE){
                    MainActivity.this.getSupportActionBar().setTitle(
                           "News - "+ mBinding.editQuery.getText().toString()
                    );

                    FetchData.getAndSetData(mAdapter,MainActivity.this,MainActivity.this,ALREADY_HAVE_DATA_BUT_REFRESH,
                            mBinding.editQuery.getText().toString());

                    mBinding.editQuery.setVisibility(View.GONE);
                }
                return true;
            }
        });

        mBinding.emptyTextView.setVisibility(View.GONE);

        FetchData.getAndSetData(mAdapter, this, MainActivity.this, Utilities.checkToRefresh(this), "");


//
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                mBinding.editQuery.setVisibility(View.VISIBLE);
                return true;
            default:
                return false;


        }
    }

    @Override
    public void onNewsClick(String url) {



                try {
                    Intent i = new Intent("android.intent.action.MAIN");
                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                catch(ActivityNotFoundException e) {
                    // Chrome is not installed
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }




    }
}