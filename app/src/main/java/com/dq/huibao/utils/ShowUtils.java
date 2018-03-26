package com.dq.huibao.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Cool on 2017/12/20.
 */

public class ShowUtils {
    private static Context mContext;
    public static void Init(Context context){
        mContext = context;
    }
    @SuppressLint("WrongConstant")
    public static void toast(String str){
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param context:
     * @param title:标题
     * @param message:提示内容
     * @param listener:事件回调
     */
    public static void showDialog(Context context,String title, String message, final OnDialogListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.confirm();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.cancel();
                    }
                });
        builder.create().show();
    }

    /**
     * dialog点击事件回调
     */
    public interface OnDialogListener{
        void confirm();
        void cancel();
    }
}
