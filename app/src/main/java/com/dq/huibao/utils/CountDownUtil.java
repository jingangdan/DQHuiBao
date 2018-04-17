package com.dq.huibao.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

/**
 * 倒计时
 * Created by d on 2018/4/8.
 */

public class CountDownUtil {
    private long time;
    CountdownThread thread;
    SimpleDateFormat formatter;
    String hms;
    List<TextView> textViews = new ArrayList<>();
    /**
     * @time:时间差(指定的一段时间长),时间戳
     * @counetdownView：TextView显示倒计时
     * */
    public CountDownUtil(long time, TextView... counetdownView) {
        this.time = time;
        textViews.addAll(Arrays.asList(counetdownView));
    }
    /**
     * 倒计时
     * */
    public void countdown(){
        formatter = new SimpleDateFormat("HH:mm:ss");// 初始化Formatter的转换格式。
        formatter.setTimeZone(TimeZone.getTimeZone("GMT +8:00"));//设置时区(北京),如果你不设置这个,你会发现你的时间总会多出来8个小时

        thread = new CountdownThread(time, 1000);// 构造CountDownTimer对象
        thread.start();
    }
    class CountdownThread extends CountDownTimer {
        public CountdownThread(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }
        @Override
        public void onTick(long millisUntilFinished) {
            hms = formatter.format(millisUntilFinished);//转化成  "00:00:00"的格式
            setTime(hms);
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            //倒计时结束时触发
            setTime("00:00:00");
        }
    }
    private void setTime(String time){
        for (TextView tv:textViews) {
            tv.setText(time);
        }
    }
    /**
     * 终止线程
     * */
    public void stopThread(){
        thread.cancel();
    }
}
