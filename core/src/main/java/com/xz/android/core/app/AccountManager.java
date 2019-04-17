package com.xz.android.core.app;

import com.blankj.utilcode.util.SPUtils;

/**
 * 账户管理
 * Created by xiongz on 2018/1/12
 */
public class AccountManager {

    private static final String LOGIN_TAG = "login_tag";

    /**
     * 保存用户登录状态，登录后调用
     */
    public static void setLoginState(boolean state) {
        SPUtils.getInstance().put(LOGIN_TAG, state);
    }

    /**
     * 是否登录
     * @return
     */
    private static boolean isLogin() {
        return SPUtils.getInstance().getBoolean(LOGIN_TAG);
    }

    /**
     * 检查是否登录
     *
     * @param checker
     */
    public static void checkAccount(IUserChecker checker) {
        if (isLogin()) {
            checker.onLogin();
        } else {
            checker.onNotLogin();
        }
    }
}
