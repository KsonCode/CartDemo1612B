package com.bwie.cartdemo1612b.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.cartdemo1612b.R;
import com.bwie.cartdemo1612b.entity.CartEntity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends XRecyclerView.Adapter<CartAdapter.MyHolder> {
    private List<CartEntity.Result>  cartList;
    private Context context;

    private notifyCart notifyCart;

    public void setNotifyCart(CartAdapter.notifyCart notifyCart) {
        this.notifyCart = notifyCart;
    }

    public CartAdapter(Context ctx, List<CartEntity.Result> cartList) {
        this.cartList = cartList;
        this.context = ctx;

    }

    public List<CartEntity.Result> getCartList() {
        return cartList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout,viewGroup,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        CartEntity.Result product = cartList.get(i);

        if (product.cartChecked){
            myHolder.checkBox.setChecked(true);
        }else{
            myHolder.checkBox.setChecked(false);
        }

        myHolder.nametv.setText(product.categoryName);
        ProductAdapter productAdapter = new ProductAdapter(context,product.shoppingCartList);

        myHolder.productRv.setLayoutManager(new LinearLayoutManager(context));

        myHolder.productRv.setAdapter(productAdapter);
        myHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notifyCart.isCheced(myHolder.checkBox.isChecked());
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartList ==null?0:cartList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView nametv;
        @BindView(R.id.rv_product)
        XRecyclerView productRv;
        @BindView(R.id.checkbox_cart)
        CheckBox checkBox;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

   public interface  notifyCart{
        void isCheced(boolean b);
    }
}
