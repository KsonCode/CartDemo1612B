package com.bwie.cartdemo1612b.entity;

import java.util.List;

public class CartEntity {

    public String message;
    public String status;
    public List<Result> result;

    /**
     * 一级对象
     */
    public static class Result{

        //一级是否选中
        public boolean cartChecked;
        public String categoryName;
        public  List<Product> shoppingCartList;

        /**
         * 二级对象
         */
        public static class Product{
            //二级是否选中
            public boolean productChecked;
            public String commodityId;
            public String commodityName;
            public String count;
            public String pic;
            public String price;

            //用户购买数量
            public int num = 1;
        }


    }

}
