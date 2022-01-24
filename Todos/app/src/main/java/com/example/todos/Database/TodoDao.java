package com.example.todos.Database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todo WHERE listName = :listName")
    LiveData<List<TodoEntry>> loadTodosOfList(String listName);

    @Insert
    void insertTodo(TodoEntry todoEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTodo(TodoEntry newTodoEntry);

    @Delete
    void deleteTodo(TodoEntry todoEntry);

    @Query("SELECT * FROM todo WHERE id = :Id")
    TodoEntry loadTodoById(int Id);

    @Query("SELECT DISTINCT listName from todo")
    LiveData<List<String>> loadListNames();

    @Query("SELECT * FROM todo")
    List<TodoEntry> loadAllTodos();

    @Query("DELETE FROM todo")
    void deleteAllTodos();

}
