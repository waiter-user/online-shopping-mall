package com.java.manager.service;

import com.java.manager.pojo.Order;
import com.java.manager.util.PageBean;

public interface OrderService {
    //分页查询
    PageBean<Order> queryByPage(Integer pageNo, Integer pageSize);

    Boolean updateZhuangTai(String id);

    Boolean deleteOrder(String id);
}
