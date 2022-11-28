package com.java.sportshop.service;

import com.java.sportshop.pojo.Order;
import com.java.sportshop.pojo.Orderitem;

import java.util.List;

public interface OrderService {
    boolean addOrder(Order order, List<Orderitem> orderitemlist);

    List<Order> getOrderList(Integer user_id);
    List<Orderitem> getOrderItem(String orderid);
}
