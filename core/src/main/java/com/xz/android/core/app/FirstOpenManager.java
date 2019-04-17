package com.xz.android.core.app;

import com.blankj.utilcode.util.SPUtils;

/**
 * 账户管理
 * Created by xiongz on 2018/12/24
 */
public class FirstOpenManager {

    private static final String FIRST_OPEN_TAG = "first_tag";

    /**
     * 设置是否第一次打开APP
     */
    public static void setFirst(boolean state) {
        SPUtils.getInstance().put(FIRST_OPEN_TAG, state);
    }

    /**
     * 是否第一次打开APP
     *
     * @return
     */
    private static boolean isFirst() {
        return SPUtils.getInstance().getBoolean(FIRST_OPEN_TAG, true);
    }

    /**
     * 检查是否第一次打开APP
     *
     * @param checker
     */
    public static void checkFirstOpen(IFirstChecker checker) {
        if (isFirst()) {
            checker.onFirstOpen();
        }
    }
}
