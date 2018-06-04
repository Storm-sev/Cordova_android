package com.efeiyi.bigwiki.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.KeyEvent;

import com.efeiyi.bigwiki.MainActivity;
import com.efeiyi.bigwiki.R;
import com.efeiyi.bigwiki.bean.IconBean;
import com.efeiyi.bigwiki.service.DownLoadNavService;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.apache.cordova.PermissionHelper;

import java.io.FilenameFilter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.http.HTTP;
import retrofit2.http.Url;
import storm_lib.RxHelper;
import storm_lib.base.BaseActivity;
import storm_lib.base.BaseObserver;
import storm_lib.httpclient.downLoad.DownLoadService;
import storm_lib.httpclient.manager.HttpClientManager;
import storm_lib.utils.AppUtils;
import storm_lib.utils.DialogHelper;
import storm_lib.utils.LogUtils;
import storm_lib.utils.PermissionManager;
import storm_lib.utils.SPUtils;
import storm_lib.utils.StringUtils;

/**
 * splash
 */
public class SplashActivity extends BaseActivity {

    public static final String TAG = SplashActivity.class.getSimpleName();

    private SPUtils navIconSPUtils;

    String fileName;

    @Override
    protected void setUpListener() {

    }

    @Override
    protected void init() {
        //request permission
        requestPermission();

        navIconSPUtils = SPUtils.getINSTACE("navIcon");

//        String absolutePath = getExternalCacheDir().getAbsolutePath();


    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }


    @SuppressLint("CheckResult")
    private void requestPermission() {

        // 权限请求
        new RxPermissions(SplashActivity.this)
                .request(PermissionManager.PERMISSION_WRITE_EXTERNAL_STORAGE,
                        PermissionManager.PERMISSION_READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            _initData();
                        } else {
                            PermissionManager.showSystemSettingPermission(SplashActivity.this);
                        }
                    }
                });


//        new RxPermissions(SplashActivity.this)
//                .requestEach(PermissionManager.PERMISSION_WRITE_EXTERNAL_STORAGE,
//                        PermissionManager.PERMISSION_READ_PHONE_STATE)
//                .subscribe(new Consumer<Permission>() {
//                    @Override
//                    public void accept(Permission permission) throws Exception {
//
//
//                        if (permission.granted) {
//
//                            LogUtils.d(TAG, "获取权限 " + permission.name);
//                            if (permission.name.equals(PermissionManager.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
//                                LogUtils.d(TAG, "sd卡权限请求通过");
//
//                            }
//
//                            if (permission.name.equals(PermissionManager.PERMISSION_READ_PHONE_STATE)) {
//                                LogUtils.d(TAG, "读取手机 phone 的状态");
//                                // 开始倒计时
//
//                            }
//                            _initData();
//                        } else if (permission.shouldShowRequestPermissionRationale) {
//                            if (permission.name.equals(PermissionManager.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
//                                Log.d(TAG, "二次权限请求 sd卡");
//                                PermissionManager.showSystemSettingPermission(SplashActivity.this);
//                                return;
//                            }
//
//                            if (permission.name.equals(PermissionManager.PERMISSION_READ_PHONE_STATE)) {
//                                LogUtils.d(TAG, "二次权限请求  手机状态的权限 ");
//                                PermissionManager.showSystemSettingPermission(SplashActivity.this);
//                                return;
//                            }
//
//                        } else { // 点击不在提醒
//
//
//                            if (permission.name.equals(PermissionManager.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
//                                LogUtils.d(TAG,"点击了不在提醒 sd卡");
//                                PermissionManager.showSystemSettingPermission(SplashActivity.this);
//                                return;
//
//                            }
//
//                            if (permission.name.equals(PermissionManager.PERMISSION_READ_PHONE_STATE)) {
//                                LogUtils.d(TAG,"点击了不在提醒 phone");
//
//                                PermissionManager.showSystemSettingPermission(SplashActivity.this);
//                                return;
//                            }
//
//                        }
//
//                    }
//                });
    }


    private void _initData() {

        if (!PermissionManager.isAllRequestPermissionGranted(this, permissions)) {
            LogUtils.d(TAG, "全部权限没有通过");

            return;
        }

        //图标检测
        checkNavigatorBarIcon();

    }


    private void checkNavigatorBarIcon() {

        DownLoadService downLoadService =
                HttpClientManager.getDownLoadService(false);

        downLoadService.navigatorBarIcon(String.valueOf(0))
                .compose(RxHelper.IO_Main())
                .subscribe(new BaseObserver<IconBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                    }

                    @Override
                    public void onNext(IconBean iconBean) {
                        super.onNext(iconBean);

                        if (iconBean.getCode() == 0) {
                            // 获取zip文件 还有开启服务
                            updateOrNormalIcon(iconBean.getData().get(0).getResource_ON().getUri());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);


                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                });


    }

    /**
     * 检测 模块图标
     */
    private void updateOrNormalIcon(String uri) {


        String url
                = "http://diich-resource.oss-cn-beijing.aliyuncs.com/image/appNavigation/NAVBAR.zip";


        if (!navIconSPUtils.getBoolean(StringUtils.zipFileName(url))) {

            // 表示已经下载已经
            if(!DownLoadNavService.LOADING_ICON_CODE) {
                DownLoadNavService.startLoadNavIconService(this, url);
            }

        }



        boolean newState = false;

        startTOMain(newState, navIconSPUtils.getBoolean(StringUtils.zipFileName(url)),StringUtils.zipFileName(url));


    }

    // 启动主页
    private void startTOMain(boolean newState, boolean isUnzip, String zipName) {

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.putExtra("newState", newState);
        intent.putExtra("isUnzip", isUnzip);
        intent.putExtra("iconFile", zipName);
        startActivity(intent);
        overridePendingTransition(R.anim.tomain_open, R.anim.tomain_close);
        finish();
    }


//    private void startLoadNavIcon(String uri) {
//
//        Intent intent = new Intent(this, DownLoadNavService.class);
//        intent.putExtra("iconUrl", uri);
//        this.startService(intent);
//        DownLoadNavService.LOADING_ICON_CODE = true;
//
//    }


    private int[] permissions = {PermissionManager.CODE_STORAGE, PermissionManager.CODE_PHONE};


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case PermissionManager.REQUEST_OPEN_APPLICATION_SETTING_CODE:

                _initData();

                break;
        }
    }


}
