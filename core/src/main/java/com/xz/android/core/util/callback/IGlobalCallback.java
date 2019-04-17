package com.xz.android.core.util.callback;

import android.support.annotation.Nullable;

/**
 * 回调接口
 * Created by xiongz on 2017/12/10.
 */
public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
