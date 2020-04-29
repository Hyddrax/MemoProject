package com.ynovandroid.memosapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.ynovandroid.memosapp.DTO.MemoDTO;
import com.ynovandroid.memosapp.object.Memo;

import java.util.Collections;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {
    private List<MemoDTO> memoList;
    private AppCompatActivity activity;

    public MemoAdapter(List<MemoDTO> memoList, AppCompatActivity activity) {
        this.memoList = memoList;
        this.activity = activity;
    }

    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCourse = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_item_list, parent, false);
        return new MemoViewHolder(viewCourse);
    }

    @Override
    public void onBindViewHolder(MemoViewHolder holder, int position) {
        holder.textViewTitleMemo.setText(memoList.get(position).title);
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    public boolean onItemMove(int positionDebut, int positionFin) {
        Collections.swap(memoList, positionDebut, positionFin);
        notifyItemMoved(positionDebut, positionFin);
        return true;
    }

    public void onItemDismiss(RecyclerView.ViewHolder viewHolder, int position) {
        if (position > -1) {
            AppDatabaseHelper.getDatabase(viewHolder.itemView.getContext()).memoDAO().delete(memoList.get(position));

            memoList.remove(position);
            notifyItemRemoved(position);
        }
    }


    public class MemoViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "MemoViewHolder";

        public TextView textViewTitleMemo;

        public MemoViewHolder(final View itemView) {
            super(itemView);
            this.textViewTitleMemo = itemView.findViewById(R.id.title_memo);


            textViewTitleMemo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new Gson();

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("lastPos", getAdapterPosition());
                    editor.apply();

                    MemoDTO memo = memoList.get(getAdapterPosition());

                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams requestParams = new RequestParams();
                    requestParams.put("memo", gson.toJson(memo));

                    client.post("http://httpbin.org/post", requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                            String retour = new String(response);

                            Gson gson = new Gson();
                            RetourWS retourWS = gson.fromJson(retour, RetourWS.class);

                            MemoDTO tmpMemo = gson.fromJson(retourWS.form.memo, MemoDTO.class);

                            Log.d(TAG, "onSuccess: " + getAdapterPosition() + " " + tmpMemo.title + " " + tmpMemo.content);
                            Log.i(TAG, gson.toJson(retourWS));
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                            Log.e(TAG, e.toString());
                        }
                    });

                    if (memo.content != null && memo.content.isEmpty()){
                        memo.content = "Content remplis car vide";
                    }


                    //memo.content = "Ceci un Content Generique ! ";


                    //memo.content = "Ceci un Content Generique !Cecntee  !Ceci un Content  !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique ! ";
                    //memo.content += "Ceci un Content Generique !Cecntee  !Ceci un Content  !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique !Ceci un Content Generique ! ";

                    if (activity.findViewById(R.id.conteneurMain_fragment) == null) {
                        Intent intent = new Intent(itemView.getContext(), ContentActivity.class);
                        intent.putExtra("memo", gson.toJson(memo));
                        itemView.getContext().startActivity(intent);
                    } else {
                        ContentFragment fragment = new ContentFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("memo", gson.toJson(memo));
                        fragment.setArguments(bundle);

                        FragmentManager fragmentManager = activity.getSupportFragmentManager();

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.conteneurMain_fragment, fragment, "memoContent");
                        fragmentTransaction.commit();
                    }


                }
            });
        }

        public class RetourWS {

            Form form;

            public class Form {
                String memo;
            }
        }
    }
}

