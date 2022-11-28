package com.java.manager.dao.impl;

import com.java.manager.dao.OrderItemDao;
import com.java.manager.pojo.Goods;
import com.java.manager.pojo.Orderitem;
import com.java.manager.util.JdbcUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderItemDaoImpl implements OrderItemDao {
    @Override
    public List<Orderitem> selectOrderItemsById(String order_id) {
        String sql="select orderItem_id,goods_name,mall_price,goods_num from t_goods t1 INNER JOIN t_orderitem t2 where t1.goods_id=t2.goods_id and order_id=?";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql, order_id);
        List<Orderitem> list=new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Orderitem orderitem=new Orderitem();
            orderitem.setOrderitem_id(Integer.parseInt(map.get("orderItem_id").toString()));
            orderitem.setGoods_num(Integer.parseInt(map.get("goods_num").toString()));
            Goods goods=new Goods();
            goods.setMall_price(Integer.parseInt(map.get("mall_price").toString()));
            goods.setGoods_name(map.get("goods_name").toString());
            orderitem.setGoods(goods);
            list.add(orderitem);
        }
        return list;
    }
}
