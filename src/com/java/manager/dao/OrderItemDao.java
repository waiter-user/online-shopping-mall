package com.java.manager.dao;

import com.java.manager.pojo.Orderitem;

import java.util.List;

public interface OrderItemDao {
    List<Orderitem> selectOrderItemsById(String order_id);
}
