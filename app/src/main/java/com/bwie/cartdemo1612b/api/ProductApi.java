package com.bwie.cartdemo1612b.api;

import com.bwie.cartdemo1612b.entity.CartEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

/**
 * 商品相关api
 */
public interface ProductApi {
    @GET(Api.GET_CART_URL)
    Observable<CartEntity> getCarts(@HeaderMap HashMap<String,String> params);
}
