package com.bwie.cartdemo1612b.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.cartdemo1612b.R;
import com.bwie.cartdemo1612b.entity.CartEntity;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends XRecyclerView.Adapter<ProductAdapter.MyHolder> {
    private List<CartEntity.Result.Product>  productList;
    private Context context;
    private OnItemclickListener onItemclickListener;

    public void setOnItemclickListener(OnItemclickListener onItemclickListener) {
        this.onItemclickListener = onItemclickListener;
    }

    public ProductAdapter(Context ctx, List<CartEntity.Result.Product> cartList) {
        this.productList = cartList;
        this.context = ctx;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item_layout,viewGroup,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        final CartEntity.Result.Product product = productList.get(i);
        if (product.productChecked){//二级商品选中
            myHolder.checkBox.setChecked(true);
        }else{//取消
            myHolder.checkBox.setChecked(false);
        }
        myHolder.priceTv.setText(product.price);
        myHolder.nametv.setText(product.commodityName);
        myHolder.numIv.setText(product.num+"");

        //首选项，配置选项
        final RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        .transform(new RoundedCorners(5));//5像素圆角
        Glide.with(context).load(product.pic).apply(options).into(myHolder.iconIv);


        myHolder.addIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myHolder.numIv.setText(product.num+"");
                product.num++;//数量加
                notifyDataSetChanged();
            }
        });

        myHolder.minusIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myHolder.numIv.setText(product.num+"");
                product.num--;
                if (product.num==0){
                    product.num = 1;
                }
                Toast.makeText(context, "不能再减了", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        myHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myHolder.checkBox.isChecked()){

                }else{

                }
            }
        });

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemclickListener!=null){
                    onItemclickListener.onItemClick(i,myHolder.itemView,product);//位置和当前view
                }
            }
        });
        myHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemclickListener!=null){
                    onItemclickListener.onLongItemClick(i,myHolder.itemView);//位置和当前view
                }
                return false;
            }
        });

//        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<CartEntity.Result.Product> list = new ArrayList<>();
//                list.add(product);
//                System.out.println(""+new Gson().toJson(list));
//            }
//        });




    }

    @Override
    public int getItemCount() {
        return productList ==null?0:productList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView nametv;
        @BindView(R.id.price)
        TextView priceTv;
        @BindView(R.id.iv_product)
        ImageView iconIv;
        @BindView(R.id.add)
        TextView addIv;
        @BindView(R.id.minus)
        TextView minusIv;
        @BindView(R.id.num)
        EditText numIv;

        @BindView(R.id.checkbox_product)
        CheckBox checkBox;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemclickListener{
        void onItemClick(int pos, View itemView, CartEntity.Result.Product product);
        void onLongItemClick(int pos,View itemView);
    }
}
