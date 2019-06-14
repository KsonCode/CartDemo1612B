package com.bwie.cartdemo1612b.entity;

import java.util.List;

public class CartEntity {

    public String message;
    public String status;
    public List<Result> result;

    public static class Result{

        public boolean cartChecked;
        public String categoryName;
        public  List<Product> shoppingCartList;

        public static class Product{
            public boolean productChecked;
            public String commodityId;
            public String commodityName;
            public String count;
            public String pic;
            public String price;

            public int num = 1;
        }


    }

}
