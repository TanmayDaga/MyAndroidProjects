package com.example.todos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.todos.Database.AppDataBase;
import com.example.todos.Database.TodoEntry;
import com.example.todos.databinding.TodosListMainBinding;

import java.util.List;

public class TodosListFragment extends Fragment {

    private String mListName;
    private TodosListMainBinding mBinding;
    private TodosRecyclerAdapter mRecyclerAdapter;


    public TodosListFragment(String listName) {
        mListName = listName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.todos_list_main, container, false);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mRecyclerAdapter = new TodosRecyclerAdapter(getContext());
        mBinding.todosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.todosRecyclerView.setAdapter(mRecyclerAdapter);

        if (mListName != null) {
            loadData();
        }


    }

    private void loadData() {
        AppExecutors.getsInstance().diskIo().execute(new Runnable() {
            @Override
            public void run() {
                LiveData<List<TodoEntry>> todoEntries = AppDataBase.getInstance(getContext()).todoDao().loadTodosOfList(mListName);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        todoEntries.observe(getViewLifecycleOwner(), new Observer<List<TodoEntry>>() {
                            @Override
                            public void onChanged(List<TodoEntry> todoEntries) {
                                mRecyclerAdapter.setData(todoEntries);
                            }
                        });
                    }
                });


            }
        });
    }
}
