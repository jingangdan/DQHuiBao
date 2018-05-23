package com.dq.huibao.ui.homepage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：web网页
 * Created by jingang on 2017/11/1.
 */

public class WebActivity extends BaseActivity {
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.progressBar1)
    ProgressBar progressBar1;

    /*接收页面传值*/
    private Intent intent;
    private String url = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        intent = getIntent();
        url = intent.getStringExtra("url");

        webView.loadUrl(url);

        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(false); //垂直不显示
        //网页加载进度
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar1.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar1.setVisibility(View.GONE);
                }
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this,"mobile");
    }

    /**
     * 网页图片放大
     * @param url
     */
    @android.webkit.JavascriptInterface
    public void loadImage(String url){

    }
//    @Override
//    protected void initWidght() {
//        super.initWidght();
//        setTitleName("web网页");
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        webView.destroy();
    }
}
