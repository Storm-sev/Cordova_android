package com.efeiyi.bigwiki.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.efeiyi.bigwiki.R;
import com.efeiyi.bigwiki.activity.SplashActivity;
import com.umeng.message.tag.TagManager;

import storm_lib.utils.LogUtils;

public class UpdateService extends Service {

    private static final String TAG = UpdateService.class.getSimpleName();

    public static final int UPDATE_CODE = 1001;

    public static boolean DOWNLOAD_CODE = false;

    private NotificationManager notificationManager;
    private NotificationCompat.Builder mBuilder;


    private int preProgress;


    private RemoteViews mNotiCustomView; // 通知栏自定义view

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        LogUtils.d(TAG, "开启下载服务");
        createNotification();
        downLoadNewVersionApk(intent);
        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 下载 apk
     *
     * @param intent
     */
    private void downLoadNewVersionApk(Intent intent) {
        if (intent == null) {
            return;
        }
        String apk_url = intent.getStringExtra("APK_URL");


    }


    // init  初始化通知栏
    private void createNotification() {

        mBuilder = new NotificationCompat.Builder(this, null);

        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotiCustomView = new RemoteViews(this.getPackageName(), R.layout.notification_update);
        mNotiCustomView.setProgressBar(R.id.pb_download, 100, 0, false);

        mNotiCustomView.setTextViewText(R.id.tv_tool_title, "非遗大百科: 10%");
        mBuilder.setContent(mNotiCustomView);
        mBuilder.setSmallIcon(R.mipmap.app_icon);
        notificationManager.notify(UPDATE_CODE, mBuilder.build());

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * 开启服务
     */
    public static void startDownLoadApp(Context context, String apkUrl) {

        Intent intent = new Intent(context, UpdateService.class);
        intent.putExtra("APK_URL", apkUrl);
        context.startService(intent);
    }
}
