package com.bwie.cartdemo1612b.api;

import com.bwie.cartdemo1612b.base.BaseResponse;
import com.bwie.cartdemo1612b.entity.CartEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 商品相关api
 */
public interface ProductApi {
    /**
     * 请求购物车接口
     * @param params 头部入参
     * @return
     */
    @GET(Api.GET_CART_URL)
    Observable<CartEntity> getCarts(@HeaderMap HashMap<String,String> params);



    @PUT("small/order/verify/v1/syncShoppingCart")
    @FormUrlEncoded
    Observable<BaseResponse> addCart(@HeaderMap HashMap<String,String> params ,@Field("data") String data);
}
