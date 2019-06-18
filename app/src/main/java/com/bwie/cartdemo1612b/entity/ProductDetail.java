package com.bwie.cartdemo1612b.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

public class ProductDetail {

    public int commodityId;
    public int count = 1;

    public ProductDetail(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }


    public int getCommodityId() {
        return this.commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
