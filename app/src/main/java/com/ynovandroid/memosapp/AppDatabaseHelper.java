package com.ynovandroid.memosapp;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseHelper {
    private static AppDatabaseHelper databaseHelper = null;
    private AppDatabase database;

    private AppDatabaseHelper(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, "memos.db").allowMainThreadQueries().build();
    }

    static synchronized AppDatabase getDatabase(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new AppDatabaseHelper(context.getApplicationContext());
        }
        return databaseHelper.database;
    }
}
