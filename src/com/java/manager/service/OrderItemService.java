package com.java.manager.service;

import com.java.manager.pojo.Orderitem;

import java.util.List;

public interface OrderItemService {
    List<Orderitem> getOrderItems(String order_id);
}
