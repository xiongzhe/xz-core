package com.xz.android.core.net.rx;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加cookie的拦截器
 * Created by xiongz on 2018/3/7.
 */
public final class AddCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(SPUtils.getInstance().getString("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cookie) throws Exception {
                        //给原生API请求附带上WebView拦截下来的Cookie
                        builder.addHeader("Cookie", cookie);
                    }
                });


        return chain.proceed(builder.build());
    }
}
