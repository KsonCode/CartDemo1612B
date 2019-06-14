package com.bwie.cartdemo1612b.presenter;

import com.bwie.cartdemo1612b.contract.CartContract;
import com.bwie.cartdemo1612b.entity.CartEntity;
import com.bwie.cartdemo1612b.model.CartModel;
import com.bwie.cartdemo1612b.net.NetCallback;

import java.util.HashMap;

public class CartPresenter implements CartContract.ICartPresenter {
    private CartModel cartModel;
    private CartContract.ICartView cartView;
    public CartPresenter(CartContract.ICartView view){
        this.cartView = view;
        cartModel = new CartModel();
    }


    /**
     * 解绑，防止内存泄漏
     */
    public void detach(){
        if (cartModel!=null){
            cartModel = null;
        }
        if (cartView!=null){
            cartView = null;
        }
        System.gc();
    }

    @Override
    public void getCarts(HashMap<String, String> params) {
        cartModel.getCarts(params,new NetCallback() {
            @Override
            public void success(CartEntity cartEntity) {
                if (cartView!=null){
                    cartView.success(cartEntity);
                }
            }

            @Override
            public void failure(String error) {
                if (cartView!=null){
                    cartView.failure(error);
                }
            }
        });
    }
}
