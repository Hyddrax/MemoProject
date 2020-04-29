package com.ynovandroid.memosapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ynovandroid.memosapp.DAO.MemoDAO;
import com.ynovandroid.memosapp.DTO.MemoDTO;

@Database(entities = {MemoDTO.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MemoDAO memoDAO();

}
