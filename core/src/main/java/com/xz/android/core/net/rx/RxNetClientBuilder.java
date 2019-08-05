package com.xz.android.core.net.rx;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 网络请求客户端建造者
 * Created by xiongz on 2017/12/13
 */
public class RxNetClientBuilder {

    private HashMap<String, Object> PARAMS;
    private HashMap<String, Object> HEADERS;
    private String mUrl = null;
    private RequestBody mBody = null;
    private File mFile = null;
    private String mFileKey = null;

    RxNetClientBuilder() {
    }

    public final RxNetClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxNetClientBuilder headers(HashMap<String, Object> headers) {
        if (headers == null) HEADERS = new HashMap<>();
        HEADERS = headers;
        return this;
    }

    public final RxNetClientBuilder params(HashMap<String, Object> params) {
        if (PARAMS == null) PARAMS = new HashMap<>();
        PARAMS = params;
        return this;
    }

    public final RxNetClientBuilder params(String key, Object value) {
        if (PARAMS == null) PARAMS = new HashMap<>();
        PARAMS.put(key, value);
        return this;
    }

    public final RxNetClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxNetClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxNetClientBuilder fileKey(String fileKey) {
        this.mFileKey = fileKey;
        return this;
    }

    public final RxNetClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxNetClient build() {
        if (PARAMS == null) PARAMS = new HashMap<>();
        if (HEADERS == null) HEADERS = new HashMap<>();
        return new RxNetClient(mUrl, PARAMS, HEADERS, mBody, mFile, mFileKey);
    }
}
