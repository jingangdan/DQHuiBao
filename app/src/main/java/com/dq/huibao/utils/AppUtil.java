package com.dq.huibao.utils;

import android.app.Activity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.dq.huibao.base.BaseActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by d on 2018/4/24.
 */

public class AppUtil {
    public static int height = 0;
    public static int width = 0;
    /**
     * 获取屏幕宽高
     */
    public static void getWindowsDef(Activity activity){
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        width = outMetrics.widthPixels;
        height = outMetrics.heightPixels;
    }

    /**
     * 获取屏幕高度
     * @param activity
     * @return
     */
    public static int getHeight(Activity activity){
        if (height == 0)
            getWindowsDef(activity);
        return height;
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getWidth(){
//        if (width == 0)
//            getWindowsDef(activity);
        return width;
    }

    /**
     *  转化时间戳
     * @param pattern:yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String getDateToString(String pattern,long date){
        String dateStr = "";
        try {
            dateStr = new java.text.SimpleDateFormat(pattern).format(new java.util.Date(date));
        }catch (Exception e){
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 文字转utf-8--转base64
     * @param str
     * @return
     */
    public static String textToUTF_8(String str){
        String s = "";
        try {
            s = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String ss = Base64.encodeToString(s.getBytes(), Base64.DEFAULT);
        ss = ss.replaceAll("[\\s*\t\n\r]", "");
        return ss;
    }
}
