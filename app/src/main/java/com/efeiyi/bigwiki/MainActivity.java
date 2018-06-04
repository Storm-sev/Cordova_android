/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.efeiyi.bigwiki;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.efeiyi.bigwiki.manager.NavigatorManager;
import com.jakewharton.rxbinding2.widget.RxRadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import storm_lib.base.BaseActivity;
import storm_lib.utils.LogUtils;

public class MainActivity extends BaseActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_news)
    RadioButton rbNews;
    @BindView(R.id.rb_wiki)
    RadioButton rbWiki;
    @BindView(R.id.rb_active)
    RadioButton rbActive;
    @BindView(R.id.rb_actihall)
    RadioButton rbActivihall;
    @BindView(R.id.bottom_navigator)
    RelativeLayout bottomNavigator;
    @BindView(R.id.rg_navigator)
    RadioGroup rgNavigator;

    @Override
    protected void setUpListener() {

        changeModuleNavigator();


    }

    @SuppressLint("CheckResult")
    private void changeModuleNavigator() {

        RxRadioGroup.checkedChanges(rgNavigator)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        switch (integer) {
                            case R.id.rb_home:

                                break;
                            case R.id.rb_news:

                                break;
                            case R.id.rb_wiki:

                                break;
                            case R.id.rb_active:

                                break;
                            case R.id.rb_actihall:

                                break;
                        }
                    }
                });


    }

    @Override
    protected void init() {

        // fullScreen(this);
        checkNavIconState();

        rgNavigator.check(R.id.rb_home);

    }

    private void checkNavIconState() {
        boolean newState = getIntent().getBooleanExtra("newState", false);
        boolean isUnzip = getIntent().getBooleanExtra("isUnzip", false);
        String iconFile = getIntent().getStringExtra("iconFile");

        if (newState && isUnzip) {
            // 启用新的图标
            NavigatorManager.setNewNavigatorIconState(iconFile, rgNavigator);
        }

//        NavigatorManager.setNewNavigatorIconState(iconFile, rgNavigator);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    /**
     * 全屏模式处理
     */
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);


            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus; // attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);

            }
        }
    }


    private long exitTime = 0;

    @Override
    public void onBackPressed() {

        // 两次退出
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
            finish();
            System.exit(0);
        }


    }
}
