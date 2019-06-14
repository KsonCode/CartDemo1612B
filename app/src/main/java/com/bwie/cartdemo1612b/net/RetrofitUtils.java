package com.bwie.cartdemo1612b.net;

import com.bwie.cartdemo1612b.api.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 手写单利模式
 */
public class RetrofitUtils {

    private static RetrofitUtils mInstance;
    private Retrofit retrofit;

    private RetrofitUtils(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static RetrofitUtils getInstance(){
        if (mInstance==null){
            synchronized (RetrofitUtils.class){
                if (mInstance==null){
                    mInstance = new RetrofitUtils();
                }
            }
        }

        return mInstance;
    }

    /**
     * 动态代理模式创建servicee对象
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> clz){

        return  retrofit.create(clz);
    }




}
