package com.bwie.cartdemo1612b.app;

import android.app.Application;
import android.content.Context;

import com.bwie.cartdemo1612b.utils.GreenDaoUtils;

public class App extends Application {
    public static Context context;//全局上下文

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        GreenDaoUtils.getInstance().init();//greendao初始化
    }
}
