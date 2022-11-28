package com.java.sportshop.dao.impl;

import com.java.sportshop.dao.OrderDao;
import com.java.sportshop.pojo.Goods;
import com.java.sportshop.pojo.Order;
import com.java.sportshop.pojo.Orderitem;
import com.java.sportshop.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    @Override
    public int insert(Order order, List<Orderitem> list){
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "insert into t_order values(?,?,0,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, order.getOrder_id());
            statement.setTimestamp(2, order.getOrder_time());
            statement.setInt(3, order.getOrder_jine());
            statement.setString(4, order.getOrder_address());
            statement.setString(5, order.getOrder_pay());
            statement.setInt(6, order.getUser().getUser_id());
            statement.executeUpdate();
            statement.close();
            sql = "insert into t_orderitem(order_id,goods_id,goods_num) values(?,?,?)";
            statement = connection.prepareStatement(sql);
            for (Orderitem orderitem : list) {
                statement.setString(1, orderitem.getOrder().getOrder_id());
                statement.setInt(2, orderitem.getGoods().getGoods_id());
                statement.setInt(3, orderitem.getGoods_num());
                statement.addBatch();
            }
            statement.executeBatch();
            statement.close();

            sql = "update t_goods set stock_num=stock_num-? where goods_id=?";
            statement=connection.prepareStatement(sql);
            for (Orderitem orderitem : list) {
                statement.setInt(1, orderitem.getGoods_num());
                statement.setInt(2, orderitem.getGoods().getGoods_id());
                statement.addBatch();
            }
            statement.executeBatch();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return 0;
        }finally {
            JdbcUtil.closeAll(statement, connection);
        }
    }

    @Override
    public List<Order> selectListByUserId(Integer userid) {
        String sql="select order_id,order_jine,order_zhuangtai,order_time from t_order where order_userid=?";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql, userid);
        List<Order> list=new ArrayList<>();
        for(Map<String,Object> map:maps){
            Order order=new Order();
            order.setOrder_id(map.get("order_id").toString());
            order.setOrder_jine(Integer.parseInt(map.get("order_jine").toString()));
            order.setOrder_zhuangtai(Integer.parseInt(map.get("order_zhuangtai").toString()));
            order.setOrder_time(Timestamp.valueOf(map.get("order_time").toString()));
            list.add(order);
        }
        return list;
    }

    @Override
    public List<Orderitem> selectOrderItem(String orderid) {
        String sql="select * from t_orderitem where order_id =?";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql, orderid);
        List<Orderitem> list=new ArrayList<>();
        for(Map<String,Object> map:maps){
            Orderitem orderitem=new Orderitem();
            Goods goods=new Goods();
            goods.setGoods_id(Integer.parseInt(map.get("goods_id").toString()));
            orderitem.setGoods(goods);
            Order order=new Order();
            order.setOrder_id(orderid);
            orderitem.setOrder(order);
            orderitem.setGoods_num(Integer.parseInt(map.get("goods_num").toString()));
            list.add(orderitem);
        }
        return list;
    }
}
