package com.architecture.wplib.common.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BindImage {
    /**
     * xml 传入url 加载图片
     * @param iv   imageView
     * @param url 图片路径
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView iv, String url) {
        Glide.with(iv.getContext()).load(url).into(iv);
    }

    /**
     * xml 传入resId 加载图片
     * @param iv    imageView
     * @param resId resource id
     */
    @BindingAdapter({"resId"})
    public static void loadMipmapResource(ImageView iv, int resId) {
        iv.setImageResource(resId);
    }
}
