package com.java.sportshop.service.impl;


import com.java.sportshop.dao.OrderDao;
import com.java.sportshop.dao.impl.OrderDaoImpl;
import com.java.sportshop.pojo.Order;
import com.java.sportshop.pojo.Orderitem;
import com.java.sportshop.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao= (OrderDao) new OrderDaoImpl();
    @Override
    public boolean addOrder(Order order, List<Orderitem> orderitemlist) {
        int insert = orderDao.insert(order, orderitemlist);
        return insert>0;
    }

    @Override
    public List<Order> getOrderList(Integer user_id) {
        return orderDao.selectListByUserId(user_id);
    }

    @Override
    public List<Orderitem> getOrderItem(String orderid) {
        return orderDao.selectOrderItem(orderid);
    }
}
