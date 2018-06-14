package storm_lib.httpclient.downLoad;

import android.widget.SimpleCursorTreeAdapter;

import com.efeiyi.bigwiki.app.MApplication;
import com.efeiyi.bigwiki.bean.VersionBean;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import storm_lib.base.BaseObserver;
import storm_lib.base.BaseSubscriber;
import storm_lib.httpclient.manager.HttpClientManager;
import storm_lib.utils.SPUtils;

/**
 * 下载管理工具类
 */
public class DownLoadManager {

    public static final String TAG = DownLoadManager.class.getSimpleName();

    /**
     * 版本检测
     *
     * @param phone
     */
    public static void checkVersion(String phone, BaseObserver<VersionBean> observer) {


        Observable<VersionBean> versionBeanObservable =
                HttpClientManager.getDownLoadService(false).checkVersion(phone);

        versionBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 更新apk
     */
    public static void loadApk(String url, final FileCallBack<ResponseBody> fileCallBack) {

        Observable<ResponseBody> downLoadNewApk =
                HttpClientManager.getDownLoadService().downLoadNewApk(url);

        downLoadNewApk.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        fileCallBack.saveFile(body);

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FileSubscriber<ResponseBody>(fileCallBack));

    }


    /**
     * 下载导航栏的图标文件
     */
    public static void loadNavigatorBarIcon(String iconUrl, final FileCallBack<ResponseBody> fileCallBack) {


        Observable<ResponseBody> downloadIcon
                = HttpClientManager.getDownLoadService(false).downloadFile(iconUrl);

        downloadIcon.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        fileCallBack.saveFile(body);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FileSubscriber<ResponseBody>(fileCallBack));

    }


    /**
     * 多个图标文件下载
     */
    public static void loadNavigatorBarIcon(final List<String> iconUrls) {

        final String iconPath = MApplication.appContext.getExternalFilesDir("").getAbsolutePath() + File.separator + "icon";
        File file = new File(iconPath);

        if (!file.exists()) { // 创建文件夹 ;
            file.mkdir();
        }


        final String fileName = "icon.png";

        for (int i = 0; i <= iconUrls.size(); i++) {


            loadNavigatorBarIcon(iconUrls.get(i), new FileCallBack<ResponseBody>(iconPath,
                    fileName, false) {

                long fileSize = 0;

                @Override
                public void onSuccess(ResponseBody body) {
                    // 获取bogy的长度

                    fileSize = body.contentLength();

                }

                @Override
                public void onStart() {

                }

                @Override
                public void onComplete() {
                    // 下载完成后

                    // 拿到longSize 对比长度
                    //检测图片是否下载完整
                    SPUtils sp_icon = SPUtils.getINSTACE("sp_icon");
                    sp_icon.put(fileName, true);
                    // 完整那么 存储到sp中的结果


                }

                @Override
                public void onError(Throwable e, String fileName) {
                    // 如果出现错误 检查网络

                    //loadNavigatorBarIcon(iconUrls.get(i), this);

                    // 为空的时 重新获取
                }


                @Override
                public void onProgress(float progress, long total) {

                }
            });


        }


    }


}
