package com.cleb.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


public abstract class LVAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> data = new ArrayList<>();
    protected int layoutID;

    public LVAdapter(Context context, List<T> data, int layoutID) {
        this.context = context;
        this.data = data;
        this.layoutID = layoutID;
    }

    public LVAdapter(Context context, int layoutID) {
        this.context = context;
        this.layoutID = layoutID;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void updateData(List<T> newData) {
        data.clear();
        this.data = newData;
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public final int getCount() {
        return data.size();
    }

    @Override
    public final T getItem(int position) {
        return data.get(position);
    }

    @Override
    public final long getItemId(int position) {
        return position;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        LVViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, null);

            holder = new LVViewHolder(context, position);

            initView(convertView, holder);

            convertView.setTag(holder);

        } else {
            holder = (LVViewHolder) convertView.getTag();

            resetView(holder);
        }

        deal(convertView, holder, position);

        return convertView;
    }

    void initView(View convertView, LVViewHolder holder) {}

    void resetView(LVViewHolder holder) {}

    abstract void deal(View convertView, LVViewHolder holder, int position);

}
