package com.efeiyi.bigwiki.fragment;

import com.efeiyi.bigwiki.R;

import storm_lib.base.BaseHtmlFragment.BaseHtmlFragment;

/**
 * 主页
 */
public class HomeHtmlFragment extends BaseHtmlFragment {


    @Override

    protected void preLoadHtmlData() {
        String s = "100M";

        mAppView.loadUrl("javascript:userData('" + s +  "')");
    }

    @Override
    protected void setUpListener() {

    }

    @Override
    protected void initData() {
        String s = "100M";

        mAppView.loadUrl("javascript:userData('" + s +  "')");

    }

    @Override
    protected String loadWebViewUrl() {
        return "file:///android_asset/www/pages/index/index.html";

//        return "file:///android_asset/www/home.html";

    }


    @Override
    protected int attachWebViewIdRes() {
        return R.id.home_web;
    }

    @Override
    protected int attachLayoutRes() {

        return R.layout.fragment_html_home;
    }


}
