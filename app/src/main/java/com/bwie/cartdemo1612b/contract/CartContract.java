package com.bwie.cartdemo1612b.contract;

import com.bwie.cartdemo1612b.entity.CartEntity;
import com.bwie.cartdemo1612b.net.NetCallback;

import java.util.HashMap;

public interface CartContract {

    /**
     * 逻辑层
     */
    interface ICartPresenter{

        void getCarts(HashMap<String,String> params);

    }

    /**
     * 数据模型层
     */
    interface ICartModel{

        void getCarts(HashMap<String,String> params,NetCallback netCallback);



    }

    /**
     * view层
     */
    interface ICartView{

        void success(CartEntity cartEntity);
        void failure(String error);

    }
}
