package org.apache.cordova.prompt;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.efeiyi.bigwiki.utils.ActionUtils;
import com.efeiyi.bigwiki.utils.LogUtils;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class PromptPlugin extends CordovaPlugin {


    public static final String TAG = PromptPlugin.class.getSimpleName();


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
//        if (action.equals("coolMethod")) {
//            String message = args.getString(0);
//            this.coolMethod(message, callbackContext);
//            return true;
//        }

        if (action.equals(ActionUtils.ACTION_SHOW_TOAST)) {
            showToast(args, callbackContext);
            return true;
        }

        if (action.equals(ActionUtils.ACTION_SHOW_DIALOG)) {
            showDialog(args, callbackContext);
            return true;
        }

        if (action.equals(ActionUtils.ACTION_SHOW_DIALOG_CLICK)) {
            showOnClickDialog(args, callbackContext);
        }


        return false;
    }

    /**
     * 支持结果回调的dialog
     *
     * @param args
     * @param callbackContext
     */
    private void showOnClickDialog(JSONArray args, CallbackContext callbackContext) {


    }

    /**
     * 不带结果回调的dialog
     *
     * @param args
     * @param callbackContext
     */
    private void showDialog(JSONArray args, CallbackContext callbackContext) throws JSONException {
        String title = args.getString(0);
        String message = args.getString(1);
        LogUtils.d(TAG, "title: " + title + " message : " + message);
        if (title != null && title.length() > 0) {
            callbackContext.success();
            // AlertDialog dialog = new AlertDialog.Builder(cordova.getContext());
            AlertDialog.Builder dialog = new AlertDialog.Builder(cordova.getActivity());

            dialog.setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("确定", null)
                    .show();
        } else {
            callbackContext.error("Expected , one non-empty string , argument");
        }


    }

    private void showToast(JSONArray args, CallbackContext callbackContext) throws JSONException {
        String msg = args.getString(0);
        if (msg != null && msg.length() > 0) {
            LogUtils.d(TAG, "");
            callbackContext.success(msg);
            Toast.makeText(cordova.getActivity(), msg, Toast.LENGTH_LONG).show();
        } else {
            callbackContext.error("Expected, one non-empty  string, argument");
        }
    }


}
