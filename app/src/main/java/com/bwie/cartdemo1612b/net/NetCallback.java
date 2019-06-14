package com.bwie.cartdemo1612b.net;

import com.bwie.cartdemo1612b.entity.CartEntity;

public interface NetCallback {
    void success(CartEntity cartEntity);
    void failure(String error);
}
