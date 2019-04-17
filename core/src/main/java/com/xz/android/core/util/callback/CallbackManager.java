package com.xz.android.core.util.callback;

import java.util.HashMap;

/**
 * 回调管理类
 * Created by xiongz on 2017/12/10.
 */
public class CallbackManager {

    private static final HashMap<Object, IGlobalCallback> CALLBACKS = new HashMap<>();

    private static class Holder {
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    public CallbackManager addCallback(Object tag, IGlobalCallback callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag) {
        return CALLBACKS.get(tag);
    }
}
