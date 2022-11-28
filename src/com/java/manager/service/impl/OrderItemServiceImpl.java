package com.java.manager.service.impl;

import com.java.manager.dao.OrderItemDao;
import com.java.manager.dao.impl.OrderItemDaoImpl;
import com.java.manager.pojo.Orderitem;
import com.java.manager.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    @Override
    public List<Orderitem> getOrderItems(String order_id) {
        return orderItemDao.selectOrderItemsById(order_id);
    }
}
