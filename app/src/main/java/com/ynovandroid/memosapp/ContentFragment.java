package com.ynovandroid.memosapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ynovandroid.memosapp.DTO.MemoDTO;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {

    public MemoDTO memo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        Gson gson = new Gson();

        this.memo = gson.fromJson(getArguments().getString("memo"), MemoDTO.class);

        TextView textView = view.findViewById(R.id.memo_content);

        textView.setText(this.memo.content);

        return view;
    }
}
