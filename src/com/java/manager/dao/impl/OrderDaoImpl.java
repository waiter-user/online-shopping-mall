package com.java.manager.dao.impl;

import com.java.manager.dao.OrderDao;
import com.java.manager.pojo.Order;
import com.java.manager.pojo.User;
import com.java.manager.util.JdbcUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Integer selectTotalCount() {
        String sql = "select count(*) total from t_order";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql);
        Map<String, Object> map = maps.get(0);
        Integer count = Integer.valueOf(map.get("total").toString());
        return count;
    }

    @Override
    public List<Order> selectList(Integer pageNo, Integer pageSize) {
        String sql = "select order_id,order_jine,order_time,order_address,order_zhuangtai from t_order limit ?,?";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql, (pageNo - 1) * pageSize, pageSize);
        List<Order> orders=new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Order order =new Order();
            order.setOrder_id(map.get("order_id").toString());
            order.setOrder_jine(Integer.valueOf(map.get("order_jine").toString()));
            order.setOrder_time(Timestamp.valueOf(map.get("order_time").toString()));
            order.setOrder_address(map.get("order_address").toString());
            order.setOrder_zhuangtai(Integer.valueOf(map.get("order_zhuangtai").toString()));
            orders.add(order);
        }
        return orders;
    }

    @Override
    public Integer updateZTById(String id) {
        Order order=this.selectOrderById(id);
        String sql="update t_order set order_zhuangtai=? where order_id=?";
        int i = JdbcUtil.executeUpdate(sql, order.getOrder_zhuangtai() + 1, order.getOrder_id());
        return i;
    }

    @Override
    public Order selectOrderById(String id) {
        String sql="select * from t_order where order_id=?";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql, id);
        Map<String, Object> map = maps.get(0);
        Order order=new Order();
        order.setOrder_id(map.get("order_id").toString());
        order.setOrder_pay(map.get("order_pay").toString());
        order.setOrder_time(Timestamp.valueOf(map.get("order_time").toString()));
        order.setOrder_zhuangtai(Integer.parseInt(map.get("order_zhuangtai").toString()));
        order.setOrder_jine(Integer.parseInt(map.get("order_jine").toString()));
        order.setOrder_address(map.get("order_address").toString());
        User user=new User();
        user.setUser_id(Integer.parseInt(map.get("order_userid").toString()));
        order.setUser(user);
        return order;
    }

    @Override
    public Integer deleteById(String id) {
        String sql="delete from t_order where order_id=?";
        int i = JdbcUtil.executeUpdate(sql, id);
        return i;
    }
}
