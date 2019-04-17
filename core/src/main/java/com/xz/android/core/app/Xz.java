package com.xz.android.core.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * 配置项
 * Created by xiongz on 2017/12/10
 */
public final class Xz {

    /**
     * 初始化配置项
     * @param context
     * @return
     */
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getXzConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     * 获取配置项单例
     * @return
     */
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    /**
     * 获取配置项
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    /**
     * 获取上下文
     * @return
     */
    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    /**
     * 获取application
     * @return
     */
    public static Application getApplication() {
        return getConfiguration(ConfigKeys.APPLICATION);
    }

    /**
     * 获取app handler
     * @return
     */
    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

}