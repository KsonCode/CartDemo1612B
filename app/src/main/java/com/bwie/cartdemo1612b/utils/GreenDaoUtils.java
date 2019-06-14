package com.bwie.cartdemo1612b.utils;

import android.database.sqlite.SQLiteDatabase;

import com.bwie.cartdemo1612b.app.App;
import com.bwie.cartdemo1612b.gen.DaoMaster;
import com.bwie.cartdemo1612b.gen.DaoSession;

public class GreenDaoUtils   {

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private volatile static GreenDaoUtils mInstance;

    private GreenDaoUtils() {

    }

    public static GreenDaoUtils getInstance() {

        if (mInstance == null) {
            synchronized (GreenDaoUtils.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoUtils();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        setDatabase();
    }


    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        devOpenHelper = new DaoMaster.DevOpenHelper(App.context, "kson-db", null);
        sqLiteDatabase = devOpenHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }
}
