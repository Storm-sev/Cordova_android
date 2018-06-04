package storm_lib.utils;

import android.content.Context;
import android.content.Intent;
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




    public static void installApk(Context context, File file) {
        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.getApplicationContext().startActivity(intent);

    }

}
