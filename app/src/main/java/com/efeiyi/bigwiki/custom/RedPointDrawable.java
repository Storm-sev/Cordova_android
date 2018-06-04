package com.efeiyi.bigwiki.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;

import com.efeiyi.bigwiki.R;

/**
 * 具有小红点的 drawable
 */
public class RedPointDrawable extends Drawable{

    public static final  String TAG= RedPointDrawable.class.getSimpleName();

    private  Drawable mDrawable;
    private Context mContext;
    private Paint mPaint;
    private int mRadius;   // 小红点半径

    private boolean mShowRedPoint;   // 是否显示红点点

    private int mGravity = Gravity.CENTER;


    public RedPointDrawable(Context context, Drawable drawable) {
       this.mContext = context;
       this.mDrawable = drawable;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.RED);
        mRadius = context.getResources().getDimensionPixelSize(R.dimen.res_point_radius_small);
    }

    public void showRedPoint(boolean showRedPoint) {
      this.mShowRedPoint = showRedPoint;
      invalidateSelf();
    }

    public void setRadius(int radius){
        this.mRadius = radius;
    }


    public void setColor(int color) {

        mPaint.setColor(color);
    }




    @Override
    public void draw(@NonNull Canvas canvas) {
        mDrawable.draw(canvas);

        if (mShowRedPoint) {
            // 获取原图标右上脚的坐标
            int cx = getBounds().right;
            int cy = getBounds().top;

//            // 计算小红点的坐标
//            if ((Gravity.LEFT & mGravity) == Gravity.LEFT) {
//                cx -= mRadius;
//            } else if ((Gravity.RIGHT & mGravity) == Gravity.RIGHT) {
//                cx += mRadius;
//            }
//
//            if ((Gravity.TOP & mGravity) == Gravity.TOP) {
//                cy -= mRadius;
//            } else if ((Gravity.BOTTOM & mGravity) == Gravity.BOTTOM) {
//                cy +=mRadius;
//            }

            canvas.drawCircle(cx, cy, mRadius, mPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mDrawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mDrawable.setColorFilter(colorFilter);

    }

    @Override
    public int getOpacity() {
        return mDrawable.getOpacity();
    }

    @Override
    public int getIntrinsicWidth() {

        return mDrawable.getIntrinsicWidth() + mRadius ;
    }

    @Override
    public int getIntrinsicHeight() {
        return mDrawable.getIntrinsicHeight() + mRadius;
    }


    @Override
    public void setBounds(@NonNull Rect bounds) {
        super.setBounds(bounds);
        mDrawable.setBounds(bounds);

    }


    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mDrawable.setBounds(left, top, right, bottom);
    }


    public static RedPointDrawable wrap(Context context, Drawable drawable) {
        if (drawable instanceof RedPointDrawable) {
            return (RedPointDrawable) drawable;
        }

        return new RedPointDrawable(context, drawable);
    }
}
