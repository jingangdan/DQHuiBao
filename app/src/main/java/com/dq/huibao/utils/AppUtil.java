package com.dq.huibao.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.dq.huibao.base.BaseActivity;

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
        Log.e("fffffffffffffffff","sssss-www="+width);
        Log.e("fffffffffffffffff","sssss-hhh="+height);
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
}
