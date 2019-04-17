package com.xz.android.core.app;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 配置key
 * Created by xiongz on 2017/11/13.
 */
@IntDef({ConfigKeys.API_HOST, ConfigKeys.APPLICATION_CONTEXT, ConfigKeys.CONFIG_READY,
        ConfigKeys.ICON, ConfigKeys.INTERCEPTOR,  ConfigKeys.NETWORK_INTERCEPTOR,
        ConfigKeys.HANDLER, ConfigKeys.ACTIVITY, ConfigKeys.JAVASCRIPT_INTERFACE_NAME,
        ConfigKeys.JAVASCRIPT_INTERFACE_OBJECT, ConfigKeys.WEB_HOST, ConfigKeys.WEB_USER_AGENT,
        ConfigKeys.WECHAT_APP_ID, ConfigKeys.WECHAT_APP_SECRET})
@Retention(RetentionPolicy.SOURCE)
public @interface ConfigKeys {
    int API_HOST = 1;
    int APPLICATION_CONTEXT = 2;
    int CONFIG_READY = 3;
    int ICON = 4;
    int INTERCEPTOR = 5;
    int NETWORK_INTERCEPTOR = 6;
    int HANDLER = 7;
    int LOADER_DELAYED = 8;
    int APPLICATION = 9;
    int ACTIVITY = 10;
    int JAVASCRIPT_INTERFACE_NAME = 11;
    int JAVASCRIPT_INTERFACE_OBJECT = 12;
    int WEB_HOST = 13;
    int WEB_USER_AGENT = 14;
    int WECHAT_APP_ID = 15;
    int WECHAT_APP_SECRET = 16;
}
