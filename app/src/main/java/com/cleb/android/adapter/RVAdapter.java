package com.cleb.android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FengMap on 2018/3/5.
 */

public abstract class RVAdapter<T> extends RecyclerView.Adapter<RVViewHolder> {
    protected Context context;
    protected List<T> data = new ArrayList<>();
    protected int layoutId;

    protected OnItemClickListener listener;

    public RVAdapter(int layoutId, List<T> data) {
        this.layoutId = layoutId;
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void removeItemData(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        RVViewHolder holder = new RVViewHolder(view);
        Log.i("RVViewHolder", "onCreateViewHolder: " + holder.hashCode());
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int position) {
        T itemData = data.get(position);
        bindData(holder, itemData);

        if (listener != null) {
            final View itemView = holder.itemView;
            final int pos = position;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(itemView, pos);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return listener.onLongClick(itemView, pos);
                }
            });
        }

        Log.i("RVViewHolder", "onBindViewHolder: " + position);
    }


    public abstract void bindData(RVViewHolder holder, T itemData);


    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnItemClickListener {
        void onClick(View itemView, int position);
        boolean onLongClick(View itemView, int position);
    }
}
