package com.efeiyi.bigwiki.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.TintableImageSourceView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.efeiyi.bigwiki.activity.SplashActivity;

/**
 * guide pager  adapter
 */
public class GuidePagerAdapter extends PagerAdapter {


    private Context mContext;

    public GuidePagerAdapter(Context context) {
        this.mContext = context;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        imageView.setBackgroundResource();
        container.addView(imageView);
        return imageView;
    }
}
