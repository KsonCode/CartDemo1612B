package com.bwie.cartdemo1612b.api;

import com.bwie.cartdemo1612b.base.BaseResponse;
import com.bwie.cartdemo1612b.entity.UploadEntity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 *
 */
public interface UserApi {

    /**
     * 单图
     * @param headers
     * @param file
     * @return
     */
    @POST(Api.UPLOAD_URL)
    @Multipart
    Observable<UploadEntity> uploadHeadPic(@HeaderMap HashMap<String, String> headers, @Part MultipartBody.Part file);

    /**
     * 多图
     * @param headers
     * @param file
     * @return
     */
    @POST(Api.UPLOAD_URL)
    @Multipart
    Observable<UploadEntity> uploadPics(@HeaderMap HashMap<String, String> headers, @Part List<MultipartBody.Part> file);


}
