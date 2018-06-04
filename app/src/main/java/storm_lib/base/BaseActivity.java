package storm_lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import butterknife.ButterKnife;


/**
 * base activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);

//        PushAgent.getInstance(this).onAppStart();   程序开始的时候调用
        init();
        initViews();
        initData();
        setUpListener();
    }


    // listener
    protected abstract void setUpListener();

    // init
    protected abstract void init();

    //view
    protected abstract void initViews();

    // data
    protected abstract void initData();

    /**
     * init lauout
     */
    protected abstract int attachLayoutRes();



    @Override
    protected void onResume() {
        super.onResume();
        // umeng
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //umeng
        MobclickAgent.onPause(this);
    }
}
