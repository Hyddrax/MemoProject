package com.ynovandroid.memosapp.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "memos")
public class MemoDTO {

    @PrimaryKey(autoGenerate = true)
    public long memoId;
    public String title;
    public String content;

    public MemoDTO() {
    }

    public MemoDTO init(String title, String content){
        this.title = title;
        this.content = content;
        return this;
    }
}
