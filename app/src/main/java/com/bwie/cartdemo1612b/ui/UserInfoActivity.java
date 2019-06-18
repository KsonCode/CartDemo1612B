package com.bwie.cartdemo1612b.ui;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bwie.cartdemo1612b.R;
import com.bwie.cartdemo1612b.api.UserApi;
import com.bwie.cartdemo1612b.entity.UploadEntity;
import com.bwie.cartdemo1612b.net.RetrofitUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }

    /**
     * 上传头像
     * @param view
     */
    public void upload(View view) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //创建文件
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/hello.jpg");

            //创建呢文件请求体对象
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);//设置content-type类型
//            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);//设置content-type类型,上传所有文件
//            RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"),file);//设置content-type类型
            //多表单上传的工具类
            MultipartBody.Part imgPart = MultipartBody.Part.createFormData("image",file.getName(),requestBody);
            HashMap<String,String> headers = new HashMap<>();
            headers.put("userId","");//动态sp里获取
            headers.put("sessionId","");
            RetrofitUtils.getInstance().createService(UserApi.class)
                    .uploadHeadPic(headers,imgPart).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UploadEntity>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(UploadEntity uploadEntity) {

                    Toast.makeText(UserInfoActivity.this, uploadEntity.message, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }

    }

    /**
     * 多图上传功能
     * @param view
     */
    public void uploads(View view) {

        List<File> files = new ArrayList<>();
        //创建文件
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/hello.jpg");
        File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/hello.jpg");

        files.add(file);
        files.add(file2);

        List<MultipartBody.Part> parts = new ArrayList<>();

        parts.add(MultipartBody.Part.createFormData("commodityId","6"));
        parts.add(MultipartBody.Part.createFormData("content","这件商品非常好"));

        for (File file1 : files) {//2

            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file1);

            MultipartBody.Part part = MultipartBody.Part.createFormData("image",file1.getName(),requestBody);

            parts.add(part);
        }

        //和单图上传一样的


    }
}
