package com.cleb.android;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cleb.android.adapter.RVAdapter;
import com.cleb.android.adapter.RVViewHolder;
import com.cleb.android.tools.ToastUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rlv);

        RecyclerView recyclerView = findViewById(R.id.rv);

        List<RVData> data = new ArrayList<>();
        for (int i=0; i<50; ++i) {
            data.add(new RVData(android.R.drawable.ic_delete, "人间" + i));
        }

        setListView(recyclerView, data);

        recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                Log.i("RVViewHolder", "onViewRecycled: " + holder.hashCode());
            }
        });

        getAsyncHttp();
    }

    private void getAsyncHttp() {
        OkHttpClient okHttpClient =new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url("http://www.baidu.com");
        requestBuilder.method("GET",null);
        Request request = requestBuilder.build();
        Call call= okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    Log.i("OkHttpClient", "cache---" + str);
                } else {
                    response.body().string();
                    String str = response.networkResponse().toString();
                    Log.i("OkHttpClient", "network---" + str);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.show(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT);
                    }
                });
            }
        });
    }

    private void setListView(RecyclerView recyclerView, List<RVData> data) {
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final RVMyAdapter rvMyAdapter = new RVMyAdapter(R.layout.item_rlv, data);
        recyclerView.setAdapter(rvMyAdapter);


        rvMyAdapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            @Override
            public void onClick(View itemView, int position) {
                Log.i("RVViewHolder", "onClick: " + position);
            }

            @Override
            public boolean onLongClick(View itemView, int position) {
                rvMyAdapter.removeItemData(position);
                return true;
            }
        });
    }


    static class RVData {
        public RVData(int resId, String tex) {
            this.resId = resId;
            this.tex = tex;
        }
        public int resId;
        public String tex;
    }


    static class RVMyAdapter extends RVAdapter<RVData> {

        public RVMyAdapter(int layoutId, List<RVData> data) {
            super(layoutId, data);
        }

        @Override
        public void bindData(RVViewHolder holder, RVData itemData) {
            holder.setImage(R.id.imageView, itemData.resId);
            holder.setText(R.id.textView, itemData.tex);
        }
    }

}
