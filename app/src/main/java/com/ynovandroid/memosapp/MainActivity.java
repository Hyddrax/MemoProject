package com.ynovandroid.memosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ynovandroid.memosapp.DTO.MemoDTO;
import com.ynovandroid.memosapp.object.Memo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<MemoDTO> listMemo;
    private MemoAdapter memoAdapter;
    private RecyclerView recyclerView;
    private ItemTouchHelper itemTouchHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int lastPos = preferences.getInt("lastPos", 0);

        Toast.makeText(this, "Last Position : " + lastPos, Toast.LENGTH_LONG).show();

        this.recyclerView = findViewById(R.id.list_memo);

        this.recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(layoutManager);

        this.listMemo = AppDatabaseHelper.getDatabase(this).memoDAO().getListeMemos();

        this.memoAdapter = new MemoAdapter(this.listMemo, this);
        this.recyclerView.setAdapter(this.memoAdapter);

        this.itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(this.memoAdapter));
        this.itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    public void addMemo(View view) {
        EditText editText = findViewById(R.id.add_memo);

        if (!editText.getText().toString().trim().isEmpty()) {
            MemoDTO nMemo = new MemoDTO().init(editText.getText().toString(), "");
            this.listMemo.add(0, nMemo);

            AppDatabaseHelper.getDatabase(this).memoDAO().insert(nMemo);

            this.memoAdapter.notifyItemInserted(0);
            this.recyclerView.smoothScrollToPosition(0);
            editText.setText("");
        }
    }
}
