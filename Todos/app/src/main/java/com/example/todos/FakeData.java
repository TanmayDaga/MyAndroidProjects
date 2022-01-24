package com.example.todos;

import com.example.todos.Database.TodoEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FakeData {

    public static List<TodoEntry> getFakeData(){
        TodoEntry k1 = new TodoEntry("sjaflk","sdfa",
        new Date(System.currentTimeMillis()),"df",Utilities.PRIORITY_HIGH);
        TodoEntry k2 = new TodoEntry("sjaflk","ds",
                new Date(System.currentTimeMillis()),"sd",Utilities.PRIORITY_LOW);
        TodoEntry k3 = new TodoEntry("sjaflk","sdfa",
                new Date(System.currentTimeMillis()),"sd",Utilities.PRIORITY_LOW);
        TodoEntry k4 = new TodoEntry("sjaflk","ds",
                new Date(System.currentTimeMillis()),"df",Utilities.PRIORITY_MEDIUM);

        List<TodoEntry> kd = new ArrayList<>();
        kd.add(k1);
        kd.add(k2);
        kd.add(k3);
        kd.add(k4);

        return kd;

    }
}
