package com.cleb.android.tools;

import android.content.Context;
import android.widget.Toast;


public class ToastUtils {
    private static Toast toast;

    public static void show(Context context, String text, int time) {
        if (toast == null) {
            toast = Toast.makeText(context, text, time);
        } else {
            toast.setText(text);
            toast.setDuration(time);
        }

        toast.show();
    }

    public static void show(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);
        }

        toast.show();
    }
    
}
