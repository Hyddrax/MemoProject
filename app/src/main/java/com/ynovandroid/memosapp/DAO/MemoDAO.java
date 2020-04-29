package com.ynovandroid.memosapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ynovandroid.memosapp.DTO.MemoDTO;

import java.util.List;

@Dao
public abstract class MemoDAO {
    @Query("SELECT * FROM memos")
    public abstract List<MemoDTO> getListeMemos();

    @Query("SELECT COUNT(*) FROM memos WHERE title = :title")
    public abstract long countCoursesParTitle(String title);

    @Insert
    public abstract void insert(MemoDTO... memos);

    @Update
    public abstract void update(MemoDTO... memos);

    @Delete
    public abstract void delete(MemoDTO... memos);
}
