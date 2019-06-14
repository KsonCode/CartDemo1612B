package com.bwie.cartdemo1612b.app;

import android.app.Application;
import android.content.Context;

import com.bwie.cartdemo1612b.net.RetrofitUtils;
import com.bwie.cartdemo1612b.utils.GreenDaoUtils;

public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        GreenDaoUtils.getInstance().init();
    }
}
