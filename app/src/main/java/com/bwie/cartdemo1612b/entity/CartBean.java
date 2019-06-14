package com.bwie.cartdemo1612b.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * greendao 缓存对象
 */
@Entity
public class CartBean {

    @Id(autoincrement = true)
    private Long id;
    private String json;//缓存的json结果
    @Generated(hash = 1208534150)
    public CartBean(Long id, String json) {
        this.id = id;
        this.json = json;
    }
    @Generated(hash = 1446963280)
    public CartBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getJson() {
        return this.json;
    }
    public void setJson(String json) {
        this.json = json;
    }


}
