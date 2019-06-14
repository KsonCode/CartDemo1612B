package com.bwie.cartdemo1612b.contract;

import com.bwie.cartdemo1612b.entity.CartEntity;
import com.bwie.cartdemo1612b.net.NetCallback;

import java.util.HashMap;

public interface CartContract {

    interface ICartPresenter{

        void getCarts(HashMap<String,String> params);

    }
    interface ICartModel{

        void getCarts(HashMap<String,String> params,NetCallback netCallback);



    }
    interface ICartView{

        void success(CartEntity cartEntity);
        void failure(String error);

    }
}
