package com.java.sportshop.pojo.cart;


import com.java.sportshop.pojo.Goods;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
//  购物车属性,有序使用linkedhashmap
    private Map<Integer,CartItem> map=new LinkedHashMap<>();
    //子功能方法
    public void addToCart(Goods goods, int quantity){
        if (map.containsKey(goods.getGoods_id())) {
            CartItem cartItem = map.get(goods.getGoods_id());
            cartItem.setQuantity(cartItem.getQuantity()+1);
        }else {
            CartItem cartItem=new CartItem();
            cartItem.setGoods(goods);
            cartItem.setQuantity(quantity);
            map.put(goods.getGoods_id(),cartItem);
        }
    }
    public Collection<CartItem> getItems(){
        Collection<CartItem> values=map.values();
        return values;
    }
    public Integer getTotalPrice(){
        Integer totalprice=0;
        for(CartItem cartItem:map.values()){
            totalprice+=cartItem.getPrice();
        }
        return totalprice;
    }
    public void updateQuantity(Integer goodsid,int quantity){
        CartItem cartItem=map.get(goodsid);
        cartItem.setQuantity(quantity);
    }
    public void removeCartItem(Integer goodsid){
        map.remove(goodsid);
    }
    public void clearCart(){
        map.clear();
    }
}
