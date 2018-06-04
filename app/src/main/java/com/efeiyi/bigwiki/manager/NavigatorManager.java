package com.efeiyi.bigwiki.manager;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.icu.util.Measure;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.efeiyi.bigwiki.R;
import com.efeiyi.bigwiki.app.MApplication;
import com.efeiyi.bigwiki.bean.IconBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import storm_lib.utils.FileUtils;
import storm_lib.utils.LogUtils;
import storm_lib.utils.StringUtils;
import storm_lib.utils.UIUtils;

/***
 * 底部导航图标管理器
 */
public class NavigatorManager {

    public static final String TAG = NavigatorManager.class.getSimpleName();


//    public static String iconPath = MApplication.appContext.getExternalFilesDir("").getAbsolutePath() + File.separator + "icon";
//
//    public static void setIconSelector(RadioButton view, IconBean iconBean) {
//        StateListDrawable stateListDrawable = new StateListDrawable();
////
////        Drawable normalDrawable = Drawable.createFromPath(new File(iconPath, iconBean.getFileName()).getAbsolutePath());
////        Drawable selectDrawable = Drawable.createFromPath(new File(iconPath, iconBean.getFileSelectName()).getAbsolutePath());
//
////        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, selectDrawable);
////        stateListDrawable.addState(new int[]{-android.R.attr.state_checked}, normalDrawable);
//
//        view.setBackground(stateListDrawable);
//
//
//    }


    // 启用新的图标
    public static void setNewNavigatorIconState(String iconFileName, RadioGroup rgNavigator) {

        String filePath = getIconPath(iconFileName);
        if (filePath == null) return;

        RadioButton homeView = rgNavigator.findViewById(R.id.rb_home);
        RadioButton newsView = rgNavigator.findViewById(R.id.rb_news);
        RadioButton wikiView = rgNavigator.findViewById(R.id.rb_wiki);
        RadioButton activeView = rgNavigator.findViewById(R.id.rb_active);
        RadioButton actihallView = rgNavigator.findViewById(R.id.rb_actihall);

//        setSelector(homeView, filePath, "home_normal.png", "home_checked.png");
//        setSelector(homeView, filePath, "news_normal.png", "news_checked.png");
//        setSelector(homeView, filePath, "wiki_normal.png", "wiki_checked.png");
//        setSelector(homeView, filePath, "active_normal.png", "active_checked.png");
//        setSelector(homeView, filePath, "actihall_normal.png", "actihall_checked.png");


        LogUtils.d(TAG, "homeView" + homeView == null);

        setSelector(homeView, filePath, "home_normal.png", "home_checked.png");
        setSelector(newsView, filePath, "home_normal.png", "home_checked.png");
        setSelector(wikiView, filePath, "home_normal.png", "home_checked.png");
        setSelector(activeView, filePath, "home_normal.png", "home_checked.png");
        setSelector(actihallView, filePath, "home_normal.png", "home_checked.png");

    }


    /**
     * set selector
     */
    private static void setSelector(RadioButton view, String filePath, String normalName, String checkedName) {
        StateListDrawable stateListDrawable = new StateListDrawable();

        String absolutePath = new File(filePath, normalName).getAbsolutePath();

        LogUtils.d(TAG, "文件路径" + absolutePath);

        Drawable normal = Drawable.createFromPath(new File(filePath, normalName).getAbsolutePath());
        Drawable checked = Drawable.createFromPath(new File(filePath, checkedName).getAbsolutePath());

        LogUtils.d(TAG, "图片的宽高" +
                UIUtils.dip2px(normal.getIntrinsicWidth()) + "==" + UIUtils.dip2px(normal.getIntrinsicHeight()));
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, checked);
        stateListDrawable.addState(new int[]{-android.R.attr.state_checked}, normal);

        stateListDrawable.setBounds(0, 0, UIUtils.dip2px(stateListDrawable.getIntrinsicWidth()), UIUtils.dip2px(stateListDrawable.getIntrinsicHeight()));

        view.setCompoundDrawablesRelative(null, null, null, stateListDrawable);

    }

//    /**
//     * 使用默认的选择器
//     */
//    public static void setIconNormalSelector(Context context, RadioButton view, @DrawableRes int res) {
//
//
//        StateListDrawable stateListDrawable = new StateListDrawable();
//
//        Drawable normal = null;
//        Drawable select = null;
//
//        switch (view.getId()) {
//            case R.id.rb_home:
//                normal = context.getResources().getDrawable(R.drawable.bg_home_icon);
//                select = context.getResources().getDrawable(R.drawable.bg_home_icon);
//
//                break;
//            case R.id.rb_news:
//                normal = context.getResources().getDrawable(R.drawable.bg_news_icon);
//                select = context.getResources().getDrawable(R.drawable.bg_news_icon);
//                break;
//            case R.id.rb_wiki:
//                normal = context.getResources().getDrawable(R.drawable.bg_wiki_icon);
//                select = context.getResources().getDrawable(R.drawable.bg_wiki_icon);
//
//                break;
//            case R.id.rb_active:
//                normal = context.getResources().getDrawable(R.drawable.bg_active_icon);
//                select = context.getResources().getDrawable(R.drawable.bg_active_icon);
//
//                break;
//            case R.id.rb_actihall:
//                normal = context.getResources().getDrawable(R.drawable.bg_activition_icon);
//                select = context.getResources().getDrawable(R.drawable.bg_activition_icon);
//
//                break;
//
//        }
//
//        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, select);
//        stateListDrawable.addState(new int[]{-android.R.attr.state_checked}, normal);
//
//        view.setBackground(stateListDrawable);
//
//
//    }


    public static String getIconPath(String zipFileName) {
        String s = StringUtils.zipFile2dir(zipFileName);

        boolean sd = FileUtils.isSD();

        if (s != null) {
            if (sd) {
                return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + StringUtils.zipFile2dir(zipFileName);
            } else {
                return FileUtils.getFileDir(MApplication.getAppContext()) + File.separator + StringUtils.zipFile2dir(zipFileName);
            }

        } else {
            return null;

        }


    }

}
