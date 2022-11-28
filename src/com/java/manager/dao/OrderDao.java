package com.java.manager.dao;

import com.java.manager.pojo.Order;

import java.util.List;

public interface OrderDao {
    //查询总的记录数
    Integer selectTotalCount();
    //查询当前页的数据集合
    List<Order> selectList(Integer pageNo, Integer pageSize);

    Integer updateZTById(String id);

    Order selectOrderById(String id);

    Integer deleteById(String id);
}
