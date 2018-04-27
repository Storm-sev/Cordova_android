package com.efeiyi.bigwiki.utils;


import android.drm.DrmStore;

/**
 * 源于js 调用原生的tag
 */
public class ActionUtils {

    public static final String TAG = ActionUtils.class.getSimpleName();

    /**
     *  toast
     */
    public static final String ACTION_SHOW_TOAST = "showToast";

    /**
     *  显示 dialog
     */
    public static final String ACTION_SHOW_DIALOG = "showDialog";


    /**
     * 显示带结果回调的dialog
     */
    public static final String ACTION_SHOW_DIALOG_CLICK = "showOnClickDialog";


    /**
     * 跳转到设置界面
     */
    public static final String ACTION_SETTRING = "startSetting";

    /**
     * 显示缓存
     */
    public static final String ACTION_SHOW_CACHE = "showCache";

    /**
     * 清除缓存
     */
    public static final String ACTION_CLEAN_CACHE = "cleanCache";

}


