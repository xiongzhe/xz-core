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
            case HttpMethod.GET:
                observable = service.get(URL, PARAMS);
                break;
            case HttpMethod.GET_HEAERS:
                observable = service.getWithHeader(HEADERS, URL, PARAMS);
                break;
            case HttpMethod.POST:
                observable = service.post(URL, PARAMS);
                break;
            case HttpMethod.POST_HEAERS:
                observable = service.postWithHeader(HEADERS, URL, PARAMS);
                break;
            case HttpMethod.POST_HEAERS_RAW:
                observable = service.postRawWithHeader(HEADERS, URL, BODY);
                break;
            case HttpMethod.POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case HttpMethod.PUT:
                observable = service.put(URL, PARAMS);
                break;
            case HttpMethod.PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case HttpMethod.DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case HttpMethod.UPLOAD:
                RequestBody requestBody1 =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                MultipartBody.Part body1 =
                        MultipartBody.Part.createFormData("upload", FILE.getName(), requestBody1);
                observable = service.upload(URL, body1);
                break;
            case HttpMethod.UPLOAD_HEADER:
                RequestBody requestBody2 =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                MultipartBody.Part body2 =
                        MultipartBody.Part.createFormData("upload", FILE.getName(), requestBody2);
                observable = service.uploadWithHeader(HEADERS, URL, body2);
                break;
            default:
                break;
        }

        return observable;
    }

    public Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public Observable<String> getWithHeaders() {
        return request(HttpMethod.GET_HEAERS);
    }

    public Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public Observable<String> postWithHeaders() {
        if (BODY == null) {
            return request(HttpMethod.POST_HEAERS);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public Observable<String> postRawWithHeader() {
        return request(HttpMethod.POST_HEAERS_RAW);
    }

    public Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    public Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public Observable<String> uploadWithHeaders() {
        return request(HttpMethod.UPLOAD_HEADER);
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