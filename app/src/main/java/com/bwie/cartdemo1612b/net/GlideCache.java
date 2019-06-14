package com.bwie.cartdemo1612b.net;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 自定义缓存路径
 */
@GlideModule
public class GlideCache extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
            super.applyOptions(context, builder);
        int diskCacheSizeBytes = 1024 * 1024 * 100; // 100 MB
        //手机app路径
        String appRootPath = context.getExternalCacheDir().getAbsolutePath();
        builder.setDiskCache(
                new DiskLruCacheFactory( appRootPath+"/WDDisk", diskCacheSizeBytes )
        );


    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }

}
