package com.java.sportshop.pojo.cart;

import com.java.sportshop.pojo.Goods;

public class CartItem {
    private Goods goods;
    private int quantity;
    private int price;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.quantity*this.goods.getMall_price();
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
