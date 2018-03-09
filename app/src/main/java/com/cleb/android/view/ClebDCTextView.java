package com.cleb.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;


public class ClebDCTextView extends TextView {

    public ClebDCTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ClebDCTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClebDCTextView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        preDraw(canvas);
        super.onDraw(canvas);
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if (left != null)
            left.setBounds(0, 0, left.getIntrinsicWidth(), left.getIntrinsicHeight());
        if (top != null)
            top.setBounds(0, 0, top.getIntrinsicWidth(), top.getIntrinsicHeight());
        if (right != null)
            right.setBounds(0, 0, right.getIntrinsicWidth(), right.getIntrinsicHeight());
        if (bottom != null)
            bottom.setBounds(0, 0, bottom.getIntrinsicWidth(), bottom.getIntrinsicHeight());

        super.setCompoundDrawables(left, top, right, bottom);
    }

    private void onCenterDraw(Canvas canvas, Drawable drawable, int gravity) {
        int drawablePadding = getCompoundDrawablePadding();
        int ratio = 1;
        float total;

        switch (gravity) {
            case Gravity.RIGHT:
                ratio = -1;
            case Gravity.LEFT:
                total = getPaint().measureText(getText().toString()) + drawable.getIntrinsicWidth() + drawablePadding + getPaddingLeft() + getPaddingRight();
                canvas.translate(ratio * (getWidth() - total) / 2, 0);
                break;
            case Gravity.BOTTOM:
                ratio = -1;
            case Gravity.TOP:
                Paint.FontMetrics fontMetrics0 = getPaint().getFontMetrics();
                total = fontMetrics0.descent - fontMetrics0.ascent + drawable.getIntrinsicHeight() + drawablePadding + getPaddingTop() + getPaddingBottom();
                canvas.translate(0, ratio * (getHeight() - total) / 2);
                break;
        }
    }


    private void preDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            if (drawables[0] != null) {
                setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                onCenterDraw(canvas, drawables[0], Gravity.LEFT);
            } else if (drawables[1] != null) {
                setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
                onCenterDraw(canvas, drawables[1], Gravity.TOP);
            } else if (drawables[2] != null) {
                setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                onCenterDraw(canvas, drawables[2], Gravity.RIGHT);
            } else if (drawables[3] != null) {
                setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
                onCenterDraw(canvas, drawables[3], Gravity.BOTTOM);
            }
        }
    }

}