package com.efeiyi.bigwiki.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.efeiyi.bigwiki.R;
import com.efeiyi.bigwiki.fragment.HomeHtmlFragment;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import storm_lib.base.BaseActivity;
import storm_lib.base.BaseHtmlFragment.BaseHtmlFragment;

public class MainHtmlActivity extends BaseActivity {


    public static final String TAG = MainHtmlActivity.class.getSimpleName();
    @BindView(R.id.fl_main)
    FrameLayout flMain;

    BaseHtmlFragment fragment;

    @Override
    protected void setUpListener() {

    }

    @Override
    protected void init() {

        fragment = new HomeHtmlFragment();

        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();

        ft.add(R.id.fl_main, fragment);

        ft.commit();
    }

    @Override
    protected void initViews() {



    }

    @Override
    protected void initData() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main_html;
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        fragment.startActivityForResult(intent
        ,requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragment.onActivityResult(requestCode, resultCode, data);
    }



}
