package com.dq.huibao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.dq.huibao.application.MyApplication;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.pingo.PinGoIndexMoreGoods;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.view.MyProgressDialog;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title 封装xUtils网络请求
 * @Authour zhoujp
 * @Time 2016年7月27日 下午3:25:21
 */
public class HttpxUtils {
    //所有dialog的集合
    private static Map<Integer,MyProgressDialog> dialogMap = new HashMap<>();
    //记录dialog的key
    private static int dialogInt = 0;
    /**
     * 发送get请求
     *
     * @param <T>
     */
//    public static <T> Callback.Cancelable Get(String url, Map<String, String> map, final Callback.CommonCallback<String> callback) {
//        RequestParams params = new RequestParams(url);
//        if (null != map) {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                params.addQueryStringParameter(entry.getKey(), entry.getValue());
//            }
//        }
//        Callback.Cancelable cancelable = x.http().get(params, callback);
//        return cancelable;
//    }

    public static <T> Callback.Cancelable Get(Activity activity,String url, Map<String, String> map, final Callback.CommonCallback<String> callback) {
//        Log.d("Http==Get=","url=" + url);
//        Log.d("Http==Get=","map=" + (map == null?"":map.toString()));
        showDialog(activity);
        errorLoginMap(map);
        final int nowDialog = dialogInt;
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                dismissDialog(nowDialog);
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                dismissDialog(nowDialog);
                callback.onError(ex, isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                dismissDialog(nowDialog);
                callback.onCancelled(cex);
            }

            @Override
            public void onFinished() {
                dismissDialog(nowDialog);
                callback.onFinished();
            }
        });
        return cancelable;
    }

    /**
     * 发送post请求
     *
     * @param <T>
     */
    public static <T> Callback.Cancelable Post(Activity activity,String url, Map<String, String> map, final Callback.CommonCallback<String> callback) {
//        Log.d("Http==Post=","url=" + url);
//        Log.d("Http==Post=","map=" + (map == null?"":map.toString()));
        showDialog(activity);
        errorLoginMap(map);
        final int nowDialog = dialogInt;
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Callback.Cancelable cancelable = x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                dismissDialog(nowDialog);
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                dismissDialog(nowDialog);
                callback.onError(ex, isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                dismissDialog(nowDialog);
                callback.onCancelled(cex);
            }

            @Override
            public void onFinished() {
                dismissDialog(nowDialog);
                callback.onFinished();
            }
        });
        return cancelable;
    }

    /**
     * 上传文件
     *
     * @param <T>
     */
    public static <T> Callback.Cancelable UpLoadFile(Activity activity,String url, Map<String, Object> map, final Callback.CommonCallback<String> callback) {
        showDialog(activity);
        final int nowDialog = dialogInt;
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                dismissDialog(nowDialog);
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                dismissDialog(nowDialog);
                callback.onError(ex, isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                dismissDialog(nowDialog);
                callback.onCancelled(cex);
            }

            @Override
            public void onFinished() {
                dismissDialog(nowDialog);
                callback.onFinished();
            }
        });
        return cancelable;
    }

    /**
     * 下载文件
     *
     * @param <T>
     */
    public static <T> Callback.Cancelable DownLoadFile(Activity activity,String url, String filepath, final Callback.CommonCallback<String> callback) {
        showDialog(activity);
        final int nowDialog = dialogInt;
        RequestParams params = new RequestParams(url);
// 设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                dismissDialog(nowDialog);
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                dismissDialog(nowDialog);
                callback.onError(ex, isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                dismissDialog(nowDialog);
                callback.onCancelled(cex);
            }

            @Override
            public void onFinished() {
                dismissDialog(nowDialog);
                callback.onFinished();
            }
        });
        return cancelable;
    }

    public class JsonResponseParser implements ResponseParser {
        // 检查服务器返回的响应头信息
        @Override
        public void checkResponse(UriRequest request) throws Throwable {
        }


        /**
         * 转换result为resultType类型的对象
         *
         * @param resultType  返回值类型(可能带有泛型信息)
         * @param resultClass 返回值类型
         * @param result      字符串数据
         * @return
         * @throws Throwable
         */
        @Override
        public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
            return new Gson().fromJson(result, resultClass);
        }
    }

    /**
     * 判断登陆失效
     */
//    public static void errorLogin(String a){
//        try {
//            AddrReturn bean = GsonUtil.gsonIntance().gsonToBean(a, AddrReturn.class);
//            if (bean.getStatus() == 0 && bean.getData().equals("用户验证错误")){
//
//            }
//        }catch (Exception e){
//
//        }
//    }

    /**
     * 判断登陆失效或者未登录
     * @param map
     */
    public static void errorLoginMap(Map<String,String> map){
//        if (map != null){
//            Toast.makeText(MyApplication.getIns(), "map="+map.toString(), Toast.LENGTH_SHORT).show();
//            try {
//                if (map.get("mid").equals("")){
//                    Toast.makeText(MyApplication.getIns(), "登陆失效,请登陆", Toast.LENGTH_SHORT).show();
//                }
//            }catch (Exception e){
//                Log.d("'ffffffffffff",e.toString());
//            }
//            try {
//                if (map.get("uid").equals("")){
//                    Toast.makeText(MyApplication.getIns(), "登陆失效,请登陆", Toast.LENGTH_SHORT).show();
//                }
//            }catch (Exception e){
//                Log.d("'ffffffffffff",e.toString());
//            }
//        }
    }
    /*开始dialog*/
    public static void showDialog(Activity activity) {
        ++dialogInt;
        try {
            MyProgressDialog myProgressDialog = new MyProgressDialog(activity);
            myProgressDialog.show();
            dialogMap.put(dialogInt,myProgressDialog);
        }catch (Exception e){
            Log.e("ffffffffffff",e.toString());
        }
    }

    /*结束dialog*/
    public static void dismissDialog(int a) {
        try {
            dialogMap.get(a).dismiss();
            dialogMap.remove(dialogMap.get(a));
        }catch (Exception e){

        }
    }
}