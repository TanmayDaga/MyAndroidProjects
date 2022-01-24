package com.example.todos.Database;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "todo")
public class TodoEntry {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private Date lastDate;


    private String listName;
    private int priority;

    public TodoEntry(String title, String description, Date lastDate, String listName, int priority) {

        this.title = title;
        this.description = description;
        this.lastDate = lastDate;
        this.listName = (listName == null) ? "My Tasks" : listName;
        this.priority = priority;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getListName() {
        return listName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }
}
