package storm_lib.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.efeiyi.bigwiki.app.MApplication;

import java.io.File;

/**
 * app 相关工具类
 */
public class AppUtils {

    public static final String TAG = AppUtils.class.getSimpleName();


    /**
     * application context
     */
    public  static Context getAppContext() {
        return MApplication.appContext;
    }


    /**
     * install app
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.getApplicationContext().startActivity(intent);

    }

    /**
     * 获取当前应用的版本号
     * @return
     */
    public static int getVersionCode(){
        PackageManager packageManager = MApplication.getAppContext().getPackageManager();

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(MApplication.getAppContext().getPackageName(), 0);
            LogUtils.d(TAG, packageInfo.versionCode);

            return packageInfo.versionCode ;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return -1;
    }




}
