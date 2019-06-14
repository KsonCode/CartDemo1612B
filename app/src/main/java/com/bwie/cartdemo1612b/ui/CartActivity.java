package com.bwie.cartdemo1612b.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.cartdemo1612b.R;
import com.bwie.cartdemo1612b.adapter.CartAdapter;
import com.bwie.cartdemo1612b.contract.CartContract;
import com.bwie.cartdemo1612b.entity.CartBean;
import com.bwie.cartdemo1612b.entity.CartEntity;
import com.bwie.cartdemo1612b.gen.CartBeanDao;
import com.bwie.cartdemo1612b.presenter.CartPresenter;
import com.bwie.cartdemo1612b.utils.GreenDaoUtils;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CartActivity extends AppCompatActivity implements XRecyclerView.LoadingListener, CartContract.ICartView ,CartAdapter.notifyCart{

    @BindView(R.id.rv_cart)
    XRecyclerView xRecyclerView;

    @BindView(R.id.checkbox)
    CheckBox checkBox;
    @BindView(R.id.totalPrice)
    TextView totalPrice;

    private Unbinder bind;//绑定对象

    private CartPresenter cartPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        bind = ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        cartPresenter = new CartPresenter(this);

//        if (){//有网
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", "159");
        params.put("sessionId", "1560500587000159");

        cartPresenter.getCarts(params);
//        }else{//无网
        //得到实体类的dao对象
        CartBeanDao cartBeanDao = GreenDaoUtils.getInstance().getDaoSession().getCartBeanDao();
        List<CartBean> cartBeans = cartBeanDao.queryBuilder().list();//遍历所有数据
        if (cartBeans != null && cartBeans.size() > 0) {
            CartBean cartBean = cartBeans.get(0);//得到第一条数据
            String result = cartBean.getJson();//得到json结构
            //转换成实体类
            CartEntity cartEntity = new Gson().fromJson(result, CartEntity.class);
            fillData(cartEntity);
        }

//        }


    }

    private CartAdapter cartAdapter;
    /**
     * 填充数据
     *
     * @param cartEntity
     */
    private void fillData(CartEntity cartEntity) {
        cartAdapter = new CartAdapter(this, cartEntity.result);
        cartAdapter.setNotifyCart(this);
        xRecyclerView.setAdapter(cartAdapter);

    }

    private void initView() {
        xRecyclerView.setLoadingListener(this);
        xRecyclerView.setLoadingMoreEnabled(true);//加载更多
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    @OnClick(R.id.checkbox)
    public void clickAll(View view) {

//        if (checkBox.isChecked()){
//            for (int i = 0; i < xRecyclerView.getChildCount(); i++) {
//                View child = xRecyclerView.getChildAt(i);
//                CheckBox checkBox = child.findViewById(R.id.checkbox_product);
//                checkBox.setChecked(true);
//            }
//        }else{
//            for (int i = 0; i < xRecyclerView.getChildCount(); i++) {
//                View child = xRecyclerView.getChildAt(i);
//                CheckBox checkBox = child.findViewById(R.id.checkbox_product);
//                checkBox.setChecked(false);
//            }
//        }

        if (checkBox.isChecked()){//全选的时候
            for (CartEntity.Result result : cartAdapter.getCartList()) {

                result.cartChecked = true;//设置一级选中状态
                for (CartEntity.Result.Product product : result.shoppingCartList) {
                    product.productChecked = true;//设置二级选中状态
                }
            }
            allPrice(true);

        }else{//反选
            for (CartEntity.Result result : cartAdapter.getCartList()) {

                result.cartChecked = false;//一级取消选中
                for (CartEntity.Result.Product product : result.shoppingCartList) {
                    product.productChecked = false;//二级取消选中
                }
            }
            allPrice(false);
        }


        cartAdapter.notifyDataSetChanged();




    }

    /**
     * 计算总价
     */
    private void allPrice(boolean b) {
        double totalPrices = 0;
        //遍历所有数据，得到价格汇总计算
        for (CartEntity.Result result : cartAdapter.getCartList()) {

            for (CartEntity.Result.Product product : result.shoppingCartList) {

                totalPrices += Double.parseDouble(product.price);
            }
        }
        if (!b){//未选中时
            totalPrices = 0;
        }
        totalPrice.setText(totalPrices+"");//选牛时
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {

        xRecyclerView.refreshComplete();

    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMore() {

        xRecyclerView.loadMoreComplete();

    }

    @Override
    public void success(CartEntity cartEntity) {
        //填充数据
        fillData(cartEntity);
        //请求成功后，缓存到greedao
        saveData(cartEntity);


    }

    /**
     * 保存到数据库
     * @param cartEntity
     */
    private void saveData(CartEntity cartEntity) {
        String result = new Gson().toJson(cartEntity);//
        CartBean cartBean = new CartBean();
        cartBean.setJson(result);
        GreenDaoUtils.getInstance().getDaoSession().getCartBeanDao().insert(cartBean);//把返回的json串保存到sqlite
    }

    @Override
    public void failure(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();

    }

    /**
     *
     * @param b
     */
    @Override
    public void isCheced(boolean b) {

    }
}
