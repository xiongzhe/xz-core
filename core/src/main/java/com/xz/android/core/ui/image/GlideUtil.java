package com.xz.android.core.ui.image;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.signature.MediaStoreSignature;

import java.io.File;

/**
 * Glide工具类
 * Created by xiongz on 2017/12/19.
 */
public class GlideUtil {

    /**
     * 加载
     *
     * @param context   上下文
     * @param url       图片链接
     * @param imageView 图片显示控件
     */
    public static void with(Context context,
                            Object url,
                            ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    /**
     * 加载(带占位符)
     *
     * @param context   上下文
     * @param url       图片链接
     * @param imageView 图片显示控件
     * @param options   占位符
     */
    public static void withOption(Context context,
                                  Object url,
                                  ImageView imageView,
                                  RequestOptions options) {
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载gif图片
     *
     * @param context   上下文
     * @param url       图片链接
     * @param imageView 图片显示控件
     */
    public static void withGif(Context context,
                               Object url,
                               ImageView imageView) {
        Glide.with(context)
                .asGif()
                .load(url)
                .into(imageView);
    }

    /**
     * 加载图片文件
     *
     * @param context
     * @param file
     * @param imageView
     */
    public static void withFile(Context context, File file, ImageView imageView) {
        Glide.with(context)
                .load(file)
                .into(imageView);
    }

    /**
     * 加载图片文件
     *
     * @param context
     * @param file
     * @param imageView
     */
    public static void withFile(Context context,
                                File file,
                                ImageView imageView,
                                RequestOptions options) {
        Glide.with(context)
                .load(file)
                .apply(options)
                .into(imageView);
    }
}
