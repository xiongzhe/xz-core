package com.xz.android.core.ui.loader;

import android.content.Context;

/**
 * 加载框2
 * Created by xiongz on 2017/12/13
 */
public class XzLoader2 {

    /**
     * 显示加载
     *
     * @param context
     */
    public static void showLoading(Context context) {
        LoaderManager.getInstance().showLoading(context);
    }

    /**
     * 停止加载
     */
    public static void dismissDialogNow() {
        LoaderManager.getInstance().dismissDialogNow();
    }

    /**
     * 停止加载
     *
     * @param delayed 延迟时间
     */
    public static void dismissDialog(long delayed) {
        LoaderManager.getInstance().dismissDialog(delayed);
    }

    /**
     * 停止加载
     */
    public static void dismissDialog() {
        LoaderManager.getInstance().dismissDialog();
    }
}
