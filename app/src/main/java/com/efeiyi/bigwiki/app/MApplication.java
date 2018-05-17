package com.efeiyi.bigwiki.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import com.efeiyi.bigwiki.BuildConfig;
import com.efeiyi.bigwiki.utils.LogUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import org.apache.cordova.LOG;


public class MApplication extends Application {

    public static final String TAG = MApplication.class.getSimpleName();

    public static Context appContext;

    public static LogUtils.Config config;

    private PushAgent mPushAgent;  // 友盟

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;
        initLog();
        initUmeng();


    }

    /**
     *
     */
    private void initUmeng() {


        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, "5afd4b20f43e4807a9000197", "umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "73c70b5ae640fedb5a5be029e0561257");


        mPushAgent = PushAgent.getInstance(this);

        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {
                LogUtils.d(TAG, "device token " + s);
            }

            @Override
            public void onFailure(String s, String s1) {

                LogUtils.d(TAG, "失败" + s + "----" + s1);
            }
        });

        // 自定义通知打开动作

        notificationOpenAction();
    }

    /**
     * 自定义通知打开动作
     */
    private void notificationOpenAction() {

        UmengNotificationClickHandler umengNotificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage uMessage) {
                super.dealWithCustomAction(context, uMessage);
                LogUtils.d(TAG, "message..." + uMessage.custom);

            }
        };

        mPushAgent.setNotificationClickHandler(umengNotificationClickHandler);

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
