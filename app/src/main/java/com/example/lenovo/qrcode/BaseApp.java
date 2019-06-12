package com.example.lenovo.qrcode;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Lenovo on 2019/6/12.
 */

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ZXingLibrary.initDisplayOpinion(this);

    }
}
