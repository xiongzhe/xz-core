package com.xz.android.core.ui.loader;

import android.content.Context;

/**
 * 加载框管理类
 *
 * @author xiongz
 * @date 2018/12/5
 */
public class LoaderManager {

    // 加载框
    private LoaderDialog mLoaderDialog;

    private static final class Holder {
        private static final LoaderManager INSTANCE = new LoaderManager();
    }

    public static LoaderManager getInstance() {
        return LoaderManager.Holder.INSTANCE;
    }

    public void showLoading(Context context) {
        mLoaderDialog = new LoaderDialog(context);
        mLoaderDialog.showLoading();
    }

    /**
     * 停止加载
     */
    public void dismissDialogNow() {
        if (mLoaderDialog != null) {
            mLoaderDialog.dismissDialogNow();
            mLoaderDialog = null;
        }
    }

    /**
     * 停止加载
     *
     * @param delayed 延迟时间
     */
    public void dismissDialog(long delayed) {
        if (mLoaderDialog != null) {
            mLoaderDialog.dismissDialog(delayed);
            mLoaderDialog = null;
        }
    }

    /**
     * 停止加载
     */
    public void dismissDialog() {
        if (mLoaderDialog != null) {
            mLoaderDialog.dismissDialog();
            mLoaderDialog = null;
        }
    }
}
