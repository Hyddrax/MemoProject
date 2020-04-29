package com.ynovandroid.memosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ynovandroid.memosapp.DTO.MemoDTO;

public class ContentActivity extends AppCompatActivity {

    public MemoDTO memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Gson gson = new Gson();
        this.memo = gson.fromJson(getIntent().getStringExtra("memo"), MemoDTO.class);

        ContentFragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("memo", gson.toJson(this.memo));
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conteneur_fragment, fragment, "memoContent");
        fragmentTransaction.commit();


    }
}
