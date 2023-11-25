package com.sjllsjlp.mqccy;

import android.app.Application;

import com.qq.e.comm.managers.GDTAdSdk;

public class CustomApplication extends Application {
    private static CustomApplication applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        GDTAdSdk.init(applicationContext, "1205843912");
    }
}
