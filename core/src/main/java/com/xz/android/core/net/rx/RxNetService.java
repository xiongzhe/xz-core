package com.xz.android.core.net.rx;

import java.util.HashMap;
import java.util.Map;

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
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 网络请求相关服务
 * Created by xiongz on 2017/12/13.
 */
public interface RxNetService {

    @GET
    Observable<String> get(@HeaderMap HashMap<String, Object> headers, @Url String url, @QueryMap HashMap<String, Object> params);

    @FormUrlEncoded
    @POST // post + x-www-form-urlencoded 参数 + header
    Observable<String> post(@HeaderMap HashMap<String, Object> headers, @Url String url, @FieldMap HashMap<String, Object> params);

    @POST // post + url参数 + header
    Observable<String> postParams(@HeaderMap HashMap<String, Object> headers, @Url String url, @QueryMap HashMap<String, Object> params);

    @POST // post + body参数 + header
    Observable<String> postRaw(@HeaderMap HashMap<String, Object> headers, @Url String url, @Body RequestBody body);

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
    Observable<String> upload(@HeaderMap HashMap<String, Object> headers, @Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST
    Observable<String> postMultipart(@HeaderMap HashMap<String, Object> headers, @Url String url, @PartMap Map<String, RequestBody> params);
}
