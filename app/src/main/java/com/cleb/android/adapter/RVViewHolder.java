package com.cleb.android.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by FengMap on 2018/3/5.
 */

public class RVViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;

    public RVViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
    }

    public View getView(int viewId) {
        View view = views.get(viewId, null);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }


    public TextView setText(int viewId, String text) {
        TextView tv = (TextView) getView(viewId);
        if (tv != null) {
            tv.setText(text);
        }
        return tv;
    }

    public ImageView setImage(int viewId, Bitmap bmp) {
        ImageView iv = (ImageView) getView(viewId);
        if (iv != null) {
            iv.setImageBitmap(bmp);
        }
        return iv;
    }

    public ImageView setImage(int viewId, int resId) {
        ImageView iv = (ImageView) getView(viewId);
        if (iv != null) {
            iv.setImageResource(resId);
        }
        return iv;
    }

    public ImageView setBackgroundImage(int viewId, int resId) {
        ImageView iv = (ImageView) getView(viewId);
        if (iv != null) {
            iv.setBackgroundResource(resId);
        }
        return iv;
    }
}
