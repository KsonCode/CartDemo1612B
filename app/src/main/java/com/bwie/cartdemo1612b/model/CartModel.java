package com.bwie.cartdemo1612b.model;

import com.bwie.cartdemo1612b.api.ProductApi;
import com.bwie.cartdemo1612b.contract.CartContract;
import com.bwie.cartdemo1612b.entity.CartEntity;
import com.bwie.cartdemo1612b.net.NetCallback;
import com.bwie.cartdemo1612b.net.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CartModel implements CartContract.ICartModel {


    @Override
    public void getCarts(HashMap<String, String> params, final NetCallback netCallback) {

        RetrofitUtils.getInstance().createService(ProductApi.class)
                .getCarts(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CartEntity>() {
                    @Override
                    public void accept(CartEntity cartEntity) throws Exception {

                        netCallback.success(cartEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        netCallback.failure("网络有异常");
                    }
                });
    }
}
