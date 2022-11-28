package com.java.sportshop.dao;

import com.java.sportshop.pojo.Order;
import com.java.sportshop.pojo.Orderitem;

import java.util.List;

public interface OrderDao {
    int insert(Order order, List<Orderitem> list);
    List<Order> selectListByUserId(Integer userid);
    List<Orderitem> selectOrderItem(String orderid);
}
