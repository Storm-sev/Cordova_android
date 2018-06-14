package com.efeiyi.bigwiki.adapter;

import android.content.Context;
import android.media.MediaDataSource;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.ArrayList;
import java.util.Date;

public class GuideImgAdapter extends PagerAdapter {

    public static final  String TAG  =  GuideImgAdapter.class.getSimpleName();

    private Context mContext;

    private ArrayList<String> mData;

    public GuideImgAdapter(Context context) {
        this.mContext = context;

        if (mData == null) {
            mData = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return 0;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    public void setData(ArrayList<String> data) {
        if (data != null && data.size() >= 0) {
            mData.removeAll(null);
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }
}
