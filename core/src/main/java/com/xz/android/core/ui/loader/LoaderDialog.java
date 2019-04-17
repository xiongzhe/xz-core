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

/**
 * 加载框
 * Created by xiongz on 2017/12/13
 */
public class LoaderDialog extends AppCompatDialog {

    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;

    private Context mContext;

    public LoaderDialog(Context context) {
        super(context, R.style.dialog);
        mContext = context;
    }

    /**
     * 显示加载框
     */
    public void showLoading() {
        ProgressBar progressBar = new ProgressBar(mContext);
        setContentView(progressBar);
        int deviceWidth = ScreenUtils.getScreenWidth();
        int deviceHeight = ScreenUtils.getScreenHeight();
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        show();
    }

    /**
     * 停止加载
     */
    public void dismissDialogNow() {
        cancel();
    }

    /**
     * 停止加载
     *
     * @param delayed 延迟时间
     */
    public void dismissDialog(long delayed) {
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
    public void dismissDialog() {
        long delayed = Xz.getConfiguration(ConfigKeys.LOADER_DELAYED);
        Xz.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissDialogNow();
            }
        }, delayed);
    }
}
