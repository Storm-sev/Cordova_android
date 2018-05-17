package com.efeiyi.bigwiki.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import com.efeiyi.bigwiki.BuildConfig;
import com.efeiyi.bigwiki.utils.LogUtils;


public class MApplication extends Application {

    public  static final String TAG = MApplication.class.getSimpleName();

    public static Context appContext;

    public static LogUtils.Config config;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;
        initLog();




    }

    private void initLog() {

        config = LogUtils.getConfig()
                .setLogSwitch(BuildConfig.DEBUG)
                .setConsoleSwitch(BuildConfig.DEBUG)
                .setGlobalTag("LJY")
                .setLog2FileSwitch(false)
                .setSingleTagSwitch(true)
                .setLogHeadSwitch(true)
                .setBorderSwitch(true)
                .setConsoleFilter(LogUtils.V)
                .setFileFilter(LogUtils.V);

        LogUtils.d(TAG, "更新log 打印");

    }

    public static Context getAppContext() {
        return appContext;
    }



}
