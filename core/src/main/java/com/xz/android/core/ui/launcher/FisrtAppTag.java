package com.xz.android.core.ui.launcher;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 请求类型
 * Created by xiongz on 2017/12/13.
 */
@StringDef({FisrtAppTag.HAS_FISRT_APP_TAG})
@Retention(RetentionPolicy.SOURCE)
public @interface FisrtAppTag {

    //是否已经第一次打开APP了
    String HAS_FISRT_APP_TAG = "HAS_FISRT_APP_TAG";
}
