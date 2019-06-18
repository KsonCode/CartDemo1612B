package com.bwie.cartdemo1612b.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.bwie.cartdemo1612b.R;
import com.bwie.cartdemo1612b.api.ProductApi;
import com.bwie.cartdemo1612b.base.BaseResponse;
import com.bwie.cartdemo1612b.entity.ProductDetail;
import com.bwie.cartdemo1612b.entity.ProductDetailBean;
import com.bwie.cartdemo1612b.gen.ProductDetailBeanDao;
import com.bwie.cartdemo1612b.net.RetrofitUtils;
import com.bwie.cartdemo1612b.utils.GreenDaoUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.greendao.query.Query;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailActivity extends AppCompatActivity {

    private String detail = "<div class=\\\"M-detailCon\\\" id=\\\"J-detailCon\\\">\\r\\n                \\r\\n    \\r\\n<!-- 商品参数 -->\\r\\n<div class=\\\"J-dc-tit-new dc-tit-new\\\"><i class=\\\"dc-tit-new-icon\\\"></i><p class=\\\"dc-title\\\">商品参数<i class=\\\"dc-title-en\\\">DETAIL</i></p></div>\\r\\n<div class=\\\"dc-info clearfix\\\">\\r\\n    <table class=\\\"dc-table fst\\\">\\r\\n        <tbody>\\r\\n            <tr>\\r\\n                                <th class=\\\"dc-table-tit\\\">彩妆功效：</th>\\r\\n                    <td>持久,纤长,浓密,卷翘</td>\\r\\n                                    <th class=\\\"dc-table-tit\\\">品牌名称：</th>\\r\\n                    <td>美丽工匠</td>\\r\\n                </tr><tr>                    <th class=\\\"dc-table-tit\\\">商品名称：</th>\\r\\n                    <td>【浓密卷翘 自然裸妆】美丽工匠 轻柔系自然裸妆假睫毛 素颜款赠胶水</td>\\r\\n                                    <th class=\\\"dc-table-tit\\\">商品编号：</th>\\r\\n                    <td>6922608612383</td>\\r\\n                </tr>        </tbody>\\r\\n    </table>\\r\\n\\r\\n</div>\\r\\n<!-- 商品参数 end -->\\r\\n\\r\\n\\r\\n<!-- 商品展示 -->\\r\\n<div class=\\\"J-dc-tit-new dc-tit-new\\\" id=\\\"J-proShow-scroll\\\"><i class=\\\"dc-tit-new-icon\\\"></i><p class=\\\"dc-title\\\">商品展示<i class=\\\"dc-title-en\\\">IMAGE</i></p></div>\\r\\n<div class=\\\"dc-img\\\">\\r\\n    <div class=\\\"dc-img-detail\\\">\\r\\n                <img class=\\\"J-mer-bigImg\\\" alt=\\\"\\\" src=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/57/6cb8f510-afe8-4e80-ae89-ceddc83451d9.jpg\\\" data-original=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/57/6cb8f510-afe8-4e80-ae89-ceddc83451d9.jpg\\\">\\r\\n                        <div class=\\\"img-6xx-bg\\\">\\r\\n            <img src=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/171/de592765-8724-404a-95ef-db6942ef2d11.jpg\\\" class=\\\"J-mer-bigImg\\\" data-original=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/171/de592765-8724-404a-95ef-db6942ef2d11.jpg\\\">\\r\\n        </div>\\r\\n                <div class=\\\"img-6xx-bg\\\">\\r\\n            <img src=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/46/a9abfc3c-89d7-48cb-aef0-5f78909b87c7.jpg\\\" class=\\\"J-mer-bigImg\\\" data-original=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/46/a9abfc3c-89d7-48cb-aef0-5f78909b87c7.jpg\\\">\\r\\n        </div>\\r\\n                <div class=\\\"img-6xx-bg\\\">\\r\\n            <img src=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/30/b7cd9afb-1edc-4aca-9cd7-af699d719ed2.jpg\\\" class=\\\"J-mer-bigImg\\\" data-original=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/30/b7cd9afb-1edc-4aca-9cd7-af699d719ed2.jpg\\\">\\r\\n        </div>\\r\\n                <div class=\\\"img-6xx-bg\\\">\\r\\n            <img src=\\\"http://s2.vipstatic.com/img/share/blank.png\\\" class=\\\"lazy J-mer-bigImg\\\" data-original=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/62/18ac0774-df13-4900-90fa-6668cf71e37d.jpg\\\">\\r\\n        </div>\\r\\n                <div class=\\\"img-6xx-bg\\\">\\r\\n            <img src=\\\"http://s2.vipstatic.com/img/share/blank.png\\\" class=\\\"lazy J-mer-bigImg\\\" data-original=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/10/e5ed2cc5-3ec1-4143-b8c5-6ef203f1f78d.jpg\\\">\\r\\n        </div>\\r\\n                <div class=\\\"img-6xx-bg\\\">\\r\\n            <img src=\\\"http://s2.vipstatic.com/img/share/blank.png\\\" class=\\\"lazy J-mer-bigImg\\\" data-original=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/91/49d65c5e-60e3-41d9-a28d-90c8888f88bc.jpg\\\">\\r\\n        </div>\\r\\n                <div class=\\\"img-6xx-bg\\\">\\r\\n            <img src=\\\"http://s2.vipstatic.com/img/share/blank.png\\\" class=\\\"lazy J-mer-bigImg\\\" data-original=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/112/5b1b3db0-59a2-400c-bbd4-fe04b547e999.jpg\\\">\\r\\n        </div>\\r\\n                <div class=\\\"img-6xx-bg\\\">\\r\\n            <img src=\\\"http://s2.vipstatic.com/img/share/blank.png\\\" class=\\\"lazy J-mer-bigImg\\\" data-original=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/39/564f6fa3-9af1-43b1-835c-f286d15fef83.jpg\\\">\\r\\n        </div>\\r\\n                <div class=\\\"img-6xx-bg\\\">\\r\\n            <img src=\\\"http://s2.vipstatic.com/img/share/blank.png\\\" class=\\\"lazy J-mer-bigImg\\\" data-original=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/72/ac8f36fb-609a-4807-a9ce-c20c1da56c23.jpg\\\">\\r\\n        </div>\\r\\n                <div class=\\\"img-6xx-bg\\\">\\r\\n            <img src=\\\"http://s2.vipstatic.com/img/share/blank.png\\\" class=\\\"lazy J-mer-bigImg\\\" data-original=\\\"http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/25/b4fea529-c7ec-46f5-ae5c-b7eb6482c19c.jpg\\\">\\r\\n        </div>\\r\\n            </div>\\r\\n    <div class=\\\"dc-img-con\\\">\\r\\n            </div>\\r\\n    <div class=\\\"dc-txt-con\\\">\\r\\n            </div>\\r\\n</div>\\r\\n<!-- 商品展示 end -->\\r\\n\\r\\n                <div class=\\\"c-safeguard\\\">\\r\\n                    <div class=\\\"c-safeguard-top\\\">\\r\\n                        <p class=\\\"c-safeguard-title\\\">100% 正品保障——中国人民财产保险为您购买的每一件商品承保</p>\\r\\n                    </div>\\r\\n                    <ul class=\\\"c-safeguard-list\\\">\\r\\n                        <li class=\\\"c-safeguard-item\\\">\\r\\n                            <span class=\\\"c-safeguard-icon-genuine\\\"></span>\\r\\n                            唯品会承诺，<br>在售商品均为正品，<br>并为每一件商品投保。\\r\\n                        </li><!--\\r\\n                    --><li class=\\\"c-safeguard-item\\\">\\r\\n                            <span class=\\\"c-safeguard-icon-research\\\"></span>\\r\\n                            若您对商品质量存疑，<br>唯品会将协助您<br>进行全面的查证调研。\\r\\n                        </li><!--\\r\\n                    --><li class=\\\"c-safeguard-item\\\">\\r\\n                            <span class=\\\"c-safeguard-icon-money\\\"></span>\\r\\n                            若检验出商品非正品，<br>中国人民财产保险将给您<br>商品售价的全额赔偿。\\r\\n                        </li>\\r\\n                    </ul>\\r\\n                </div>\\r\\n            <div class=\\\"J-dc-tit-new dc-tit-new\\\">\\r\\n                <i class=\\\"dc-tit-new-icon\\\"></i>\\r\\n                <p class=\\\"dc-title\\\">品牌介绍<i class=\\\"dc-title-en\\\">BRAND INTRODUCTION</i></p>\\r\\n            </div>\\r\\n            <div class=\\\"dc-img-detail clearfix\\\">\\r\\n                <img class=\\\"lazy\\\" src=\\\"//shop.vipstatic.com/img/te/ui-loading-goods.gif\\\" data-original=\\\"http://a.vpimg2.com/upload/merchandise/ugcaudit/2018/09/28/76/a198f75c-c2e2-11e8-9fc3-14187737f303.jpg\\\">\\r\\n            </div>\\r\\n            </div>";
    private String id = "50";

    @BindView(R.id.detailView)
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initData();
    }

    @OnClick(R.id.findCart)
    public void find(View view){
        startActivity(new Intent(this,CartActivity.class));
    }

    @OnClick(R.id.addCart)
    public void click(View view)  {

        HashMap<String,String> headerParams = new HashMap<>();
        headerParams.put("userId","6366");
        headerParams.put("sessionId","15605649738176366");



        ProductDetailBeanDao productDetailBeanDao = GreenDaoUtils.getInstance().getDaoSession().getProductDetailBeanDao();
        //        //查询数据库，query对象负责查询数据
        Query query = productDetailBeanDao.queryBuilder().build();//这是构建者模式
        ProductDetailBean productDetailBean = new ProductDetailBean(id,1);
        String detailData = "";


        //把数据库列表转换成本地集合
        List<ProductDetailBean> list = query.list();
        List<ProductDetail> lists = new ArrayList<>();
        String decode = "";
        if (list!=null&&list.size()>0){//从数据库取数据
//            list.add(productDetailBean);

            try {
                for (ProductDetailBean detailBean : list) {
//                    productDetailBeanDao.insertOrReplace(productDetailBean);
                    if (!id.contains(detailBean.commodityId)){//首次加
                        productDetailBeanDao.getSession().insert(productDetailBean);
                    }else{
//                        int count = detailBean.getCount();
//                        count++;
                        detailBean.count++;//
                        productDetailBeanDao.getSession().update(detailBean);
                    }
                    ProductDetail productDetail = new ProductDetail(Integer.parseInt(detailBean.getCommodityId()),detailBean.getCount());
                    lists.add(productDetail);
                }
                detailData = new Gson().toJson(lists);
                decode = URLDecoder.decode(detailData, "utf-8");
//                decode = new String(detailData.getBytes("unicode"),"utf-8");
                System.out.println("detail===="+detailData);

                RetrofitUtils.getInstance().createService(ProductApi.class)
                        .addCart(headerParams,decode).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<BaseResponse>() {
                            @Override
                            public void accept(BaseResponse baseResponse) throws Exception {
                                Toast.makeText(ProductDetailActivity.this, baseResponse.message, Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
//        else{//从来没有缓存过，创建当前对象，转换成json
//            List<ProductDetailBean> list1 = new ArrayList<>();
//            list1.add(productDetailBean);
//            detailData = new Gson().toJson(list);
//        }







    }

    private void initData() {
        initSettings();
    }

    /**
     * 初始化设置
     */
    private void initSettings() {

        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadData(detail,"text/html","utf-8");//中文乱码
        webView.loadData(detail,"text/html;charset=UTF-8",null);//解决中文乱码

    }

    @Subscribe(sticky = true)
    public void receiveId(String id) {

        this.id = id;

    }


}
