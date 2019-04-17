package com.xz.android.core.net.rx;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Rx网络请求api管理类
 * Created by xiongz on 2017/12/18.
 */
public class RxApiManager implements RxActionManager {

    private static RxApiManager mInstance = null;

    // 网络请求列表map
    private Map<Context, CompositeDisposable> mDisMap;

    /**
     * 获取单例
     *
     * @return
     */
    public static RxApiManager getInstance() {
        if (mInstance == null) {
            synchronized (RxApiManager.class) {
                if (mInstance == null) {
                    mInstance = new RxApiManager();
                }
            }
        }
        return mInstance;
    }

    private RxApiManager() {
        mDisMap = new HashMap<>();
    }

    @Override
    public void add(Context context, Disposable disposable) {
        CompositeDisposable compositeDisposable;
        if (mDisMap.containsKey(context)) {
            compositeDisposable = mDisMap.get(context);
        } else {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void remove(Context context, Disposable disposable) {
        if (mDisMap.containsKey(context)) {
            CompositeDisposable compositeDisposable = mDisMap.get(context);
            compositeDisposable.remove(disposable);
        }
    }

    @Override
    public void cancel(Context context) {
        if (mDisMap.containsKey(context)) {
            CompositeDisposable compositeDisposable = mDisMap.get(context);
            compositeDisposable.clear();
        }
    }
}
