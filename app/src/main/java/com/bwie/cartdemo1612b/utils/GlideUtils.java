package com.bwie.cartdemo1612b.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.cartdemo1612b.R;
import com.bwie.cartdemo1612b.app.App;

/**
 *
 */
public class GlideUtils {

    public static void showImg(String url, ImageView view){
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.ic_launcher);
        Glide.with(App.context).load(url).into(view);
    }
}
