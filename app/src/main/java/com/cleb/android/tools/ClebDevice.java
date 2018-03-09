package com.cleb.android.tools;

import android.util.DisplayMetrics;

/**
 * 客户端设备信息。
 *
 * @author yangbin
 * @version 1.0.0
 */
public class ClebDevice {
    protected static DisplayMetrics sDisplayMetrics;

    /**
     * 像素单位
     */
    public static final int UNIT_PX = 0;

    /**
     * DIP单位
     */
    public static final int UNIT_DIP = 1;

    /**
     * SP单位
     */
    public static final int UNIT_SP = 2;


    protected ClebDevice() {
    }

    /**
     * 获取设备屏幕密度。
     *
     * @return 返回设备屏幕密度
     */
    public static float getDeviceDensity() {
        return sDisplayMetrics.density;
    }

    /**
     * 获取设备屏幕的像素宽度。
     *
     * @return 返回设备像素宽度
     */
    public static int getDeviceWidth() {
        return sDisplayMetrics.widthPixels;
    }

    /**
     * 获取设备屏幕的像素高度。
     *
     * @return 返回设备像素高度
     */
    public static int getDeviceHeight() {
        return sDisplayMetrics.heightPixels;
    }

    /**
     * 获取设备的DPI，每英寸有多少个点。
     *
     * @return 返回设备的DPI
     */
    public static int getDeviceDensityDpi() {
        return sDisplayMetrics.densityDpi;
    }

    /**
     * 将dp和sp类型的转化对应的像素。
     * @param unit  转化单位类型
     * @param value 需要转化的值
     * @return 像素值
     */
    public static float applyDimension(int unit, float value) {
        switch (unit) {
            case UNIT_PX:
                break;
            case UNIT_DIP:
                value = value * sDisplayMetrics.density;
                break;
            case UNIT_SP:
                value = value * sDisplayMetrics.scaledDensity;
                break;
            default:
                break;
        }
        return value;
    }
}
