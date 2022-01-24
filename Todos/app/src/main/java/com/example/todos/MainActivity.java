package com.example.todos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.todos.Database.AppDataBase;
import com.example.todos.Database.TodoEntry;
import com.example.todos.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TodosViewPagerAdapter mPagerAdapter;
    private ActivityMainBinding mBinding;
    public List<String> listNames;
    private static final  String  LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mPagerAdapter = new TodosViewPagerAdapter(getSupportFragmentManager(),
                getLifecycle());

        mBinding.viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mBinding.viewPager.setAdapter(mPagerAdapter);



        new TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(listNames.get(position));
            }
        }).attach();

        loadListNames();
        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Details.class));
            }
        });

    }

    /**
     * SETS THE LIST NAMES AND ALSO SETS THE PAGER ADAPTER
     */
    private void loadListNames() {
        final String[] s = new String[1];
        AppExecutors.getsInstance().diskIo().execute(new Runnable() {
            @Override
            public void run() {
               LiveData<List<String>> listNamesLiveData = AppDataBase.getInstance(MainActivity.this).todoDao().loadListNames();

               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       listNamesLiveData.observe(MainActivity.this, new Observer<List<String>>() {
                           @Override
                           public void onChanged(List<String> strings) {
                               listNames = strings;
                               mPagerAdapter.setListNames(listNames);
                               Utilities.storeListNames(getApplicationContext(),strings);


                           }
                       });
                   }
               });


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_new_list:
                addNewList();

            case R.id.action_delete_all_todos:
                AppExecutors.getsInstance().diskIo().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDataBase.getInstance().todoDao().deleteAllTodos();
                    }
                });
            default:
                return false;
        }
    }

    private void addNewList(){

    }
}
