package com.cleb.android.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class LVViewHolder {
    private SparseArray<View> views;
    private int position;

    public LVViewHolder(Context context, int position) {
        this.position = position;
        this.views = new SparseArray<>();
    }


    public void initViews(View convertView, int[] resIds) {
        views = new SparseArray<>(resIds.length);
        for (int i=0; i<resIds.length; ++i) {
            views.put(resIds[i], convertView.findViewById(resIds[i]));
        }
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId 资源文件
     * @param text   文字
     * @return
     */
    public LVViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public LVViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public LVViewHolder setViewBgColor(int viewId, int bgColor) {
        View view = getView(viewId);
        view.setBackgroundColor(bgColor);
        return this;
    }

    public LVViewHolder setVisibility(int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    public LVViewHolder setViewBgDrawable(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId     资源文件
     * @param drawableId 图片
     * @return
     */
    public LVViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId 资源文件
     * @param bitmap 图片
     * @return
     */
    public LVViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public int getPosition() {
        return position;
    }

    public LVViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

}
