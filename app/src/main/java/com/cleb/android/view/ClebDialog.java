package com.cleb.android.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 自定义视图。
 * Created by yangbin on 16/8/31.
 */

public class ClebDialog extends Dialog {
    private Context mContext;
    private View mView;
    private int mWidth;
    private int mHeight;

    public ClebDialog(Context context) {
        super(context);
        mContext = context;

        // 设置对话框关闭之前对话框之外的区域不可点击
        setCanceledOnTouchOutside(false);
    }

    public ClebDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;

        // 设置对话框关闭之前对话框之外的区域不可点击
        setCanceledOnTouchOutside(false);
    }

    public void setCustomContentView(int layoutId) {
        View v = LayoutInflater.from(mContext).inflate(layoutId, null);
        setCustomContentView(v);
    }

    public View getContentView() {
        return mView;
    }

    public void setCustomContentView(View view) {
        mView = view;

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        setContentView(mView, lp);
    }


    public void measure() {
        Window dialogWindow = this.getWindow();

        // 设置软盘初始隐藏，推动
        dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mView.measure(0, 0);

        int w = mView.getMeasuredWidth();
        int h = mView.getMeasuredHeight();

        ViewGroup.LayoutParams lp = mView.getLayoutParams();
        lp.width = (mWidth > 0 ? mWidth : w);
        lp.height = (mHeight > 0 ? mHeight : h);

        mView.requestLayout();
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    @Override
    public void show() {
        measure();
        super.show();
    }

    public View getChild(int resId) {
        return mView.findViewById(resId);
    }

    public void setText(int resId, String text, float textSize, int color) {
        TextView tv = (TextView) mView.findViewById(resId);
        tv.setText(text);
        tv.setTextSize(textSize);
        tv.setTextColor(color);
    }

    public void setText(int resId, String text) {
        TextView tv = (TextView) mView.findViewById(resId);
        tv.setText(text);
    }
}
