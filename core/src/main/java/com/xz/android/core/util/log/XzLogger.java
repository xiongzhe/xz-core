package com.xz.android.core.util.log;

import com.xz.android.core.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * 日志
 * Created by xiongz on 2017/12/13.
 */
public class XzLogger {

    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;

    //控制log等级
    private static int LEVEL = VERBOSE;

    public static void v(String tag, String message) {
        if (LEVEL <= VERBOSE && BuildConfig.isDebug) {
            Logger.t(tag).v(message);
        }
    }

    public static void d(String tag, Object message) {
        if (LEVEL <= DEBUG && BuildConfig.isDebug) {
            Logger.t(tag).d(message);
        }
    }

    public static void d(Object message) {
        if (LEVEL <= DEBUG && BuildConfig.isDebug) {
            Logger.d(message);
        }
    }

    public static void i(String tag, String message) {
        if (LEVEL <= INFO && BuildConfig.isDebug) {
            Logger.t(tag).i(message);
        }
    }

    public static void w(String tag, String message) {
        if (LEVEL <= WARN && BuildConfig.isDebug) {
            Logger.t(tag).w(message);
        }
    }

    public static void json(String tag, String message) {
        if (LEVEL <= WARN && BuildConfig.isDebug) {
            Logger.t(tag).json(message);
        }
    }

    public static void e(String tag, String message) {
        if (LEVEL <= ERROR && BuildConfig.isDebug) {
            Logger.t(tag).e(message);
        }
    }
}
