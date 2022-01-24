package com.example.todos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todos.Database.TodoEntry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class TodosRecyclerAdapter extends RecyclerView.Adapter<TodosRecyclerAdapter.TodosViewHolder> {

    private static final String DATE_FORMAT = "dd/MM/yyy";
    private List<TodoEntry> mTodoEntries;

    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    private Context mContext;

    public TodosRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TodosRecyclerAdapter.TodosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.todo_list_item, parent, false);
        return new TodosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodosRecyclerAdapter.TodosViewHolder holder, int position) {
        holder.priorityView.setBackgroundColor(
                Utilities.getColorIdFromPriority(mContext,mTodoEntries.get(position).getPriority()));

        holder.todoTitleTextView.setText(mTodoEntries.get(position).getTitle());
        holder.doneCheckBox.setChecked(false);
    }

    @Override
    public int getItemCount() {
        if (mTodoEntries == null) {

            return 0;
        }
        return mTodoEntries.size();

    }


    class TodosViewHolder extends RecyclerView.ViewHolder {

        public TextView todoTitleTextView;
        public CheckBox doneCheckBox;
        public View priorityView;

        public TodosViewHolder(@NonNull View itemView) {
            super(itemView);
            todoTitleTextView = itemView.findViewById(R.id.todoTitleTextView);
            doneCheckBox = itemView.findViewById(R.id.todoDoneCheckBox);
            priorityView = itemView.findViewById(R.id.priorityDotView);
        }
    }

    public void setData(List<TodoEntry> todoEntries) {
        mTodoEntries = todoEntries;
        notifyDataSetChanged();
    }
}
