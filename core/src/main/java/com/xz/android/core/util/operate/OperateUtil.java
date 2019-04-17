package com.xz.android.core.util.operate;

/**
 * 用户操作工具类
 *
 * @author xiongz
 * @date 2018/12/27
 */
public class OperateUtil {

    private static final int MIN_DELAY_TIME = 500;  // 两次点击间隔不能少于1000ms
    private static long lastClickTime;

    /**
     * 是否快速连续点击按钮
     *
     * @return
     */
    public static boolean isFastClick() {
        boolean flag = false;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) < MIN_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = currentClickTime;
        return flag;
    }
}
