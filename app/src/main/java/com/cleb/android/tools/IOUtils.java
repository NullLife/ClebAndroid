package com.cleb.android.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class IOUtils {
    /**
     * 判断是否有内存卡
     * @return 返回true 存在，否则不存在
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回sdcard路径,若是没有内存卡则返回null
     * @return
     */
    public static String getSDPath() {
        if(hasSdcard())
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        return null;
    }


    public static byte[] readAssetsFile(Context c, String filePath) {
        InputStream is   = null;
        ByteArrayOutputStream baos = null;
        BufferedInputStream bis  = null;

        try {
            is = c.getAssets().open(filePath);
            baos = new ByteArrayOutputStream(is.available());
            bis = new BufferedInputStream(is);
            int bufferSize = 1024;
            int len = 0;
            byte[] buffer = new byte[bufferSize];
            while((len = bis.read(buffer, 0, bufferSize))!=-1){
                baos.write(buffer, 0, len);
            }

            return baos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null)
                    is.close();
                if(bis!=null)
                    bis.close();
                if(baos!=null)
                    baos.close();
            } catch (Exception e2) {
            }
        }

        return null;
    }

    /**
     * 写文件
     * @param dstFile    文件路径
     * @param data       数据
     * @throws Exception
     */
    public static void writeFile(File dstFile, byte[] data, boolean append) throws Exception{
        FileOutputStream fos = null ;
        fos = new FileOutputStream(dstFile, append);
        fos.write(data);
        fos.flush();
        if(fos!=null) {
            fos.close();
        }
    }

    public static Bitmap readAssetsBitmap(Context c, String picName) {
        try {
            InputStream is     = c.getAssets().open(picName);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return  bitmap;
        } catch (IOException e) {
        }
        return null;
    }

    public static Bitmap readAssetsBitmap(Context c, String picName, BitmapFactory.Options options) {
        try {
            InputStream is     = c.getAssets().open(picName);
            Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
            is.close();
            return  bitmap;
        } catch (IOException e) {
        }
        return null;
    }

}
