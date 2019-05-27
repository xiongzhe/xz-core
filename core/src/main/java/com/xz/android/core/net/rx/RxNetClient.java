package com.xz.android.core.net.rx;

import com.xz.android.core.net.HttpMethod;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

import static com.xz.android.core.net.HttpMethod.DELETE;
import static com.xz.android.core.net.HttpMethod.GET;
import static com.xz.android.core.net.HttpMethod.POST;
import static com.xz.android.core.net.HttpMethod.POST_PARAMS;
import static com.xz.android.core.net.HttpMethod.POST_RAW;
import static com.xz.android.core.net.HttpMethod.PUT;
import static com.xz.android.core.net.HttpMethod.PUT_RAW;
import static com.xz.android.core.net.HttpMethod.UPLOAD;

/**
 * 网络请求客户端
 * Created by xiong on 2017/12/12
 */
public class RxNetClient {

    private HashMap<String, Object> PARAMS;
    private HashMap<String, Object> HEADERS;
    private String URL;
    private RequestBody BODY;
    private File FILE;

    RxNetClient(String url,
                HashMap<String, Object> params,
                HashMap<String, Object> headers,
                RequestBody body,
                File file) {
        this.PARAMS = params;
        this.HEADERS = headers;
        this.URL = url;
        this.BODY = body;
        this.FILE = file;
    }

    public static RxNetClientBuilder builder() {
        return new RxNetClientBuilder();
    }

    /**
     * 请求
     *
     * @param method
     * @return
     */
    private Observable<String> request(String method) {
        Retrofit retrofit = RetrofitManager.getInstance().createRetrofit();
        RxNetService service = retrofit.create(RxNetService.class);
        Observable<String> observable = null;
        switch (method) {
            case GET:
                observable = service.get(HEADERS, URL, PARAMS);
                break;
            case POST:
                observable = service.post(HEADERS, URL, PARAMS);
                break;
            case POST_PARAMS:
                observable = service.postParams(HEADERS, URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(HEADERS, URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("upload", FILE.getName(), requestBody);
                observable = service.upload(HEADERS, URL, body);
                break;
            default:
                break;
        }

        return observable;
    }

    public Observable<String> get() {
        return request(GET);
    }

    public Observable<String> post() {
        if (BODY == null) {
            return request(POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(POST_RAW);
        }
    }

    public Observable<String> postParams() {
        if (BODY == null) {
            return request(POST_PARAMS);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(POST_RAW);
        }
    }

    public Observable<String> postRaw() {
        return request(POST_RAW);
    }

    public Observable<String> put() {
        if (BODY == null) {
            return request(PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(PUT_RAW);
        }
    }

    public Observable<String> delete() {
        return request(DELETE);
    }

    public Observable<String> upload() {
        return request(UPLOAD);
    }

    /**
     * 下载
     *
     * @return
     */
    public Observable<ResponseBody> download() {
        Retrofit retrofit = RetrofitManager.getInstance().createRetrofit();
        RxNetService service = retrofit.create(RxNetService.class);
        return service.download(URL, PARAMS);
    }
}