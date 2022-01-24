package com.example.todos;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.todos.Database.TodoEntry;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TodosViewPagerAdapter extends FragmentStateAdapter {

    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private static final String LOG_TAG = TodosViewPagerAdapter.class.getSimpleName();

    List<String> mListNames;

    public TodosViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);

    }


    @Override
    public Fragment createFragment(int position) {

        try {

            return new TodosListFragment(mListNames.get(position));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return (mListNames == null) ? 0 : mListNames.size();
    }

    public void setListNames(List<String> listNames) {
        mListNames = listNames;
        if (listNames != null) {
            for (String i :
                    listNames) {
                Log.d(LOG_TAG, i);
            }
        }
        notifyDataSetChanged();
    }

}
