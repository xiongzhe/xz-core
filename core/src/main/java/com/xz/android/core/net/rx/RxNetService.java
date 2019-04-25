package com.xz.android.core.net.rx;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 网络请求相关服务
 * Created by xiongz on 2017/12/13.
 */
public interface RxNetService {

    @GET
    Observable<String> get(@Url String url, @QueryMap HashMap<String, Object> params);

    @GET
    Observable<String> getWithHeader(@HeaderMap HashMap<String, Object> headers, @Url String url, @QueryMap HashMap<String, Object> params);

    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap HashMap<String, Object> params);

    @POST
    Observable<String> postWithHeader(@HeaderMap HashMap<String, Object> headers, @Url String url, @QueryMap HashMap<String, Object> params);

    @POST
    Observable<String> postRawWithHeader(@HeaderMap HashMap<String, Object> headers, @Url String url, @Body RequestBody body);

    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap HashMap<String, Object> params);

    @PUT
    Observable<String> putRaw(@Url String url, @Body RequestBody body);

    @DELETE
    Observable<String> delete(@Url String url, @QueryMap HashMap<String, Object> params);

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap HashMap<String, Object> params);

    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part MultipartBody.Part file);

    @POST
    @Multipart
    Observable<String> uploadWithHeader(@HeaderMap HashMap<String, Object> headers, @Url String url, @Part MultipartBody.Part file);
}
