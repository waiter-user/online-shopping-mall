package com.java.manager.service.impl;

import com.java.manager.dao.OrderDao;
import com.java.manager.dao.impl.OrderDaoImpl;
import com.java.manager.pojo.Order;
import com.java.manager.service.OrderService;
import com.java.manager.util.PageBean;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    @Override
    public PageBean<Order> queryByPage(Integer pageNo, Integer pageSize) {
        PageBean<Order> pb=new PageBean<>();
        pb.setPageNo(pageNo);
        pb.setPageSize(pageSize);
        Integer totalCount = orderDao.selectTotalCount();
        pb.setTotalCount(totalCount);
        List<Order> orders = orderDao.selectList(pageNo, pageSize);
        pb.setData(orders);
        return pb;
    }

    @Override
    public Boolean updateZhuangTai(String id) {
        Integer i = orderDao.updateZTById(id);
        return i>0;
    }

    @Override
    public Boolean deleteOrder(String id) {
        Integer i = orderDao.deleteById(id);
        return i>0;
    }
}
