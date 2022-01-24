package com.example.todos;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.CheckBox;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utilities {

    public static final int PRIORITY_HIGH = 3;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 1;

    public static final String PRIORITY_HIGH_STRING  = "Priority High";
    public static final String PRIORITY_MEDIUM_STRING = "Priority Medium";
    public static final String PRIORITY_LOW_STRING = "Priority Low";

    private static final String SHARED_PREFERENCES_LIST_NAMES  = "listNamesString";

    public static final int WORK_DONE = 89;
    public static final int WORK_NOT_DONE = 90;

    public static int getColorIdFromPriority(Context context, int priority) {

        switch (priority) {

            case PRIORITY_HIGH:
                return context.getColor(R.color.priority_high);
            case PRIORITY_MEDIUM:
                return context.getColor(R.color.priority_medium);
            default:
                return context.getColor(R.color.priority_low);

        }
    }

    public static int getPriorityFromPosition(int position){
        switch (position){
            case 0:return PRIORITY_HIGH;
            case 1:return PRIORITY_MEDIUM;
            default:return PRIORITY_LOW;
        }
    }

    public static int getWorkStatusFromCheckBox(@NonNull CheckBox checkBox) {
        return checkBox.isChecked() ? WORK_DONE : WORK_NOT_DONE;
    }

    public static String dateFormatter(int year,int month, int day){
        return String.format("%d/%d/%d", day,month,year);
    }

    public static Date intToDate(int year,int month, int day){
        return new Date(day,month,year);
    }

    public static void storeListNames(Context context, List<String> listNames){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(listNames);
        editor.putStringSet(SHARED_PREFERENCES_LIST_NAMES,set);
        editor.apply();

    }

    public static Set<String> getListNames(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> set = sp.getStringSet(SHARED_PREFERENCES_LIST_NAMES,null);
        return set;
    }

    public static String[] getPriorityList(){
        return new String[]{PRIORITY_HIGH_STRING,PRIORITY_MEDIUM_STRING,PRIORITY_LOW_STRING};
    }






}
