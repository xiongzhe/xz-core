package com.xz.android.core.ui.launcher;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 欢迎页跳转tag
 *
 * @author xiongz
 * @date 2018/9/4
 */
@IntDef({LauncherStartTag.SCROLL, LauncherStartTag.LOGIN, LauncherStartTag.MAIN})
@Retention(RetentionPolicy.SOURCE)
public @interface LauncherStartTag {
    int SCROLL = 1;
    int LOGIN = 2;
    int MAIN = 3;
}
