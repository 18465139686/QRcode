package com.example.lenovo.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WedActivity extends AppCompatActivity {

    private WebView mWed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wed);
        initView();

        Intent intent=getIntent();
        String name = intent.getStringExtra("name");

        //获取webview的设置对象
        WebSettings settings = mWed.getSettings();
        //设置webview支持js代码
        settings.setJavaScriptEnabled(true);

        //设配手机屏幕
        // 支持viewport标签适配手机屏幕
        settings.setUseWideViewPort(true);
         //将h5页面的内容缩放到屏幕宽度以内
        settings.setLoadWithOverviewMode(true);

        //在本页面打开WebView
        mWed.setWebViewClient(new WebViewClient());

        mWed.loadUrl(name);
    }

    private void initView() {
        mWed = (WebView) findViewById(R.id.wed);
    }
}
