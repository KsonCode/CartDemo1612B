package com.bwie.cartdemo1612b.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ProductDetailBean {
    @Id(autoincrement = true)
    public Long id;
    public String commodityId;
    public int count = 1;

    public ProductDetailBean(String commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    @Generated(hash = 1935991696)
    public ProductDetailBean(Long id, String commodityId, int count) {
        this.id = id;
        this.commodityId = commodityId;
        this.count = count;
    }

    @Generated(hash = 764105882)
    public ProductDetailBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommodityId() {
        return this.commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
