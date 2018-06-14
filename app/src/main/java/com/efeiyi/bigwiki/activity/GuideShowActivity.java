package com.efeiyi.bigwiki.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.efeiyi.bigwiki.R;
import com.efeiyi.bigwiki.adapter.GuideImgAdapter;
import com.efeiyi.bigwiki.adapter.GuidePagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import storm_lib.base.BaseActivity;

/**
 * 引导页展示页面
 */
public class GuideShowActivity extends BaseActivity {


    public static final String TAG = GuideShowActivity.class.getSimpleName();


    @BindView(R.id.vp_guide)
    ViewPager vpGuide;

    GuideImgAdapter mAdapter;

    ArrayList<String> data;


    @Override
    protected void setUpListener() {

    }

    @Override
    protected void init() {
        mAdapter = new GuideImgAdapter(this);


    }

    @Override
    protected void initViews() {
        mAdapter.setData(data);
        vpGuide.setAdapter(mAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_guide_show;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public static void startSelf(Context activity) {

        // 启动 查看页面
        Intent intent = new Intent(activity, GuideShowActivity.class);
        activity.startActivity(intent);

    }
}
