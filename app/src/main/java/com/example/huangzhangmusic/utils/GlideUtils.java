package com.example.huangzhangmusic.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.example.huangzhangmusic.App;
import com.example.huangzhangmusic.R;
import com.google.android.material.imageview.ShapeableImageView;

public class GlideUtils {

    private Context mContext= App.Companion.getContext();

    private static GlideUtils mInstance=null;

    // 加载为四个都是圆角的图片 可以设置圆角幅度
    RequestOptions options = new RequestOptions()
            .bitmapTransform(new RoundedCorners(DipPx.dip2px(mContext, 20)));


    public static GlideUtils getInstance() {
        if (mInstance!=null){
            return mInstance;
        }
        mInstance=new GlideUtils();
        return mInstance;
    }

    /**
     * 设置图片 方形钝角
     * @param url
     * @param view
     */
    public void setImageSrcByRectangle(String url, ShapeableImageView view){
        Glide.with(mContext).load(url).apply(options).into(new ImageViewTarget<Drawable>(view) {
            @Override
            protected void setResource(@Nullable Drawable resource) {
                view.setImageDrawable(resource);
            }
        });
    }

    public void setImageSrc(String url, ShapeableImageView view){
        Glide.with(mContext).load(url).into(new ImageViewTarget<Drawable>(view) {
            @Override
            protected void setResource(@Nullable Drawable resource) {
                view.setImageDrawable(resource);
            }
        });
    }

}
