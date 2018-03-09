package com.cleb.android.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cleb.android.tools.ClebDevice;


public class ClebPopupWindow {
    private Context mContext;
    private View mView;
    private PopupWindow mWindow;

    private int mWidth, mHeight;

    public ClebPopupWindow(Context context, int layoutId) {
        mView = LayoutInflater.from(context).inflate(layoutId, null);
        mContext = context;

        init();
    }

    public ClebPopupWindow(Context context, View view) {
        mView = view;
        mContext = context;

        init();
    }


    private void init() {
        mWindow = new PopupWindow(mContext);
        mWindow.setContentView(mView);
        mWindow.setOutsideTouchable(false);

        mView.setFocusable(true);   //
        mView.setFocusableInTouchMode(true);   //

        // 点击外面空白区域不关闭窗口
        mWindow.setFocusable(false);

        mWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
    }


    public View getConvertView() {
        return mView;
    }


    public void setOutsideTouchable(boolean touchable) {
        mWindow.setOutsideTouchable(touchable);
    }


    public View getChildView(int id) {
        return mView.findViewById(id);
    }


    public void setWidth(int width) {
        mWidth = width;
    }

    public void setHeight(int height) {
        mHeight = height;
    }


    public void setText(int resId, String content) {
        TextView tv = (TextView) mView.findViewById(resId);
        tv.setText(content);
    }


    public void measureWindow() {
        // 强制计算
        mWindow.getContentView().measure(0, 0);

        int width = mWindow.getContentView().getMeasuredWidth();
        int height = mWindow.getContentView().getMeasuredHeight();

        mWindow.setWidth(mWidth > 0 ? mWidth : width);
        mWindow.setHeight(mHeight > 0 ? mHeight : height);

//        int maxWidth = (int) (0.8f * FMDevice.getDeviceWidth());
//        int maxHeight = (int) (0.8f * FMDevice.getDeviceHeight());
//
//        mWindow.setWidth(width > maxWidth ? maxWidth : width);
//        mWindow.setHeight(height > maxHeight ? maxHeight : height);
    }


    public boolean isShowing() {
        return mWindow.isShowing();
    }


    public void show(boolean needMeasureWindow) {
        if (needMeasureWindow)
            measureWindow();

        mWindow.showAtLocation(mView,
                               Gravity.NO_GRAVITY,
                               (ClebDevice.getDeviceWidth() - mWindow.getWidth()) / 2 ,
                               (ClebDevice.getDeviceHeight() - mWindow.getHeight()) / 2);
    }

    public void show(int gravity, int x, int y, boolean needMeasureWindow) {
        if (needMeasureWindow)
            measureWindow();

        // First param can be any view if it can get the window.
        mWindow.showAtLocation(mView,
                               gravity,
                               x ,
                               y);
    }


    public void showAsDropDown(View anchor, int offsetX, int offsetY, boolean needMeasureWindow) {
        if (needMeasureWindow)
            measureWindow();
        mWindow.showAsDropDown(anchor, offsetX, offsetY);
    }

    public void update() {
        mWindow.update();
    }

    private float mStartX;
    private float mStartY;
    private float mOffsetX;
    private float mOffsetY;
    private static float MIN_HEIGHT = 60;

    public void openSwipeDownGesture() {
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View pView, MotionEvent e) {

                int act = e.getAction() & MotionEvent.ACTION_MASK;
                if (act == MotionEvent.ACTION_DOWN) {//按下
                    mStartX = e.getX();
                    mStartY = e.getY();
                    return true;
                }

                if (act == MotionEvent.ACTION_MOVE) {//移动
                    return true;
                }

                if (act == MotionEvent.ACTION_UP) {//抬起
                    mOffsetX = e.getX() - mStartX;
                    mOffsetY = e.getY() - mStartY;
                }

                int height = mWindow.getHeight();
                int minHeight = height > MIN_HEIGHT ? (int) MIN_HEIGHT : height * 2 / 3;
                if (mOffsetY >= minHeight) {
                    close();
                    return true;
                }

                return false;
            }
        });
    }


    public void close() {
        mWindow.dismiss();
    }

}
