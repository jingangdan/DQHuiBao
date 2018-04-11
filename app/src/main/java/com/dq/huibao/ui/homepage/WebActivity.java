package com.dq.huibao.ui.homepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.dq.huibao.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：web网页
 * Created by jingang on 2017/11/1.
 */

public class WebActivity extends Activity {
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
    }

//    @Override
//    protected void initWidght() {
//        super.initWidght();
//        setTitleName("web网页");
//    }
}
