package com.xz.android.core.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.ScreenUtils;
import com.xz.android.core.R;
import com.xz.android.core.app.ConfigKeys;
import com.xz.android.core.app.Xz;

import java.util.ArrayList;

/**
 * 加载框
 * Created by xiongz on 2017/12/13
 */
public class XzLoader {

    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;

    //加载框列表
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    public static void showLoading(Context context) {

        AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
//        final LottieAnimationView lottieAnimationView = new LottieAnimationView(context);
//        lottieAnimationView.setAnimation("loading.json");
//        lottieAnimationView.setRepeatCount(ValueAnimator.INFINITE);
//        lottieAnimationView.playAnimation();
        ProgressBar progressBar = new ProgressBar(context);
        dialog.setContentView(progressBar);

        int deviceWidth = ScreenUtils.getScreenWidth();
        int deviceHeight = ScreenUtils.getScreenHeight();

        Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    /**
     * 停止加载
     */
    public static void stopLoading() {
        try {
            for (AppCompatDialog dialog : LOADERS) {
                if (dialog != null) {
                    if (dialog.isShowing()) {
                        dialog.cancel();
                    }
                }
            }
            LOADERS.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止加载
     */
    public static void dismissDialogNow() {
        stopLoading();
    }

    /**
     * 停止加载
     *
     * @param delayed 延迟时间
     */
    public static void dismissDialog(long delayed) {
        Xz.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissDialogNow();
            }
        }, delayed);
    }

    /**
     * 停止加载
     */
    public static void dismissDialog() {
        long delayed = Xz.getConfiguration(ConfigKeys.LOADER_DELAYED);
        Xz.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissDialogNow();
            }
        }, delayed);
    }
}
