package com.java.manager.dao.impl;

import com.java.manager.dao.GoodsDao;
import com.java.manager.pojo.Catelog;
import com.java.manager.pojo.Goods;
import com.java.manager.util.JdbcUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public Integer selectCountAll(String keyword) {
        String s1 = "select count(*) total from (";
        StringBuffer sb1 = new StringBuffer(s1);
        String s2 = "select goods_id,goods_name,goods_pic,goods_miaoshu,catelog_name,market_price,mall_price,stock_num," +
                "enter_date from t_goods t1 inner join t_catelog t2 on t1.catelog_id=t2.catelog_id";
        StringBuffer sb2 = new StringBuffer(s2);
        if (null != keyword && !"".equals(keyword)) {
            //满足条件时，拼接字符串
            sb2.append(" where goods_name like '%" + keyword + "%'");
        }
        sb1.append(sb2.toString());
        sb1.append(") temp");
        String sql = sb1.toString();
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql);
        Map<String, Object> map = maps.get(0);
        Integer i = Integer.valueOf(map.get("total").toString());
        return i;
    }

    @Override
    public List<Goods> selectList(String keyword, Integer pageNo, Integer pageSize) {
        String sql = "select goods_id,goods_name,goods_pic,goods_miaoshu,catelog_name,market_price,mall_price,stock_num," +
                "enter_date from t_goods t1 inner join t_catelog t2 on t1.catelog_id=t2.catelog_id";
        StringBuffer sb = new StringBuffer(sql);
        if (null != keyword && !"".equals(keyword)) {
            //满足 条件时，拼接字符串
            sb.append(" where goods_name like '%" + keyword + "%'");
        }
        sb.append(" limit ?,?");
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sb.toString(), (pageNo - 1) * pageSize, pageSize);
        List<Goods> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Goods goods = new Goods();
            goods.setGoods_id(Integer.valueOf(map.get("goods_id").toString()));
            goods.setGoods_name(map.get("goods_name").toString());
            goods.setGoods_pic(map.get("goods_pic").toString());
            goods.setGoods_miaoshu(map.get("goods_miaoshu").toString());
            //封装一个catelog对象
            Catelog catelog = new Catelog();
            catelog.setCatelog_name(map.get("catelog_name").toString());
            //建立商品与类别之间的关联
            goods.setCatelog(catelog);
            goods.setMarket_price(Integer.valueOf(map.get("market_price").toString()));
            goods.setMall_price(Integer.valueOf(map.get("mall_price").toString()));
            goods.setStock_num(Integer.valueOf(map.get("stock_num").toString()));
            goods.setEnter_date(Date.valueOf(map.get("enter_date").toString()));
            list.add(goods);
        }
        return list;
    }

    @Override
    public Goods selectById(Integer id) {
        String sql = "select goods_id,goods_name,market_price,mall_price,catelog_id from t_goods where goods_id=?";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql, id);
        Map<String, Object> map = maps.get(0);
        Goods goods = new Goods();
        goods.setGoods_id(Integer.valueOf(map.get("goods_id").toString()));
        goods.setGoods_name(map.get("goods_name").toString());
        goods.setMarket_price(Integer.valueOf(map.get("market_price").toString()));
        goods.setMall_price(Integer.valueOf(map.get("mall_price").toString()));
        Catelog catelog = new Catelog();
        catelog.setCatelog_id(Integer.valueOf(map.get("catelog_id").toString()));
        goods.setCatelog(catelog);
        return goods;
    }

    @Override
    public int update(Goods goods) {
        String sql="update t_goods set goods_name=?,market_price=?,mall_price=?,catelog_id=? where goods_id=?";
        int i = JdbcUtil.executeUpdate(sql, goods.getGoods_name(), goods.getMarket_price(), goods.getMall_price(), goods.getCatelog().getCatelog_id(), goods.getGoods_id());
        return i;
    }

    @Override
    public int insert(Goods goods) {
        String sql="insert into t_goods(goods_name,goods_mianshu,goods_pic,market_price,mall_price,catelog_id,stock_num,goods_address,enter_date) "+
                "values(?,?,?,?,?,?,?,?,?)";
        Object[] params={goods.getGoods_name(),goods.getGoods_miaoshu(),goods.getGoods_pic(),goods.getMarket_price(),goods.getMall_price(),goods.getCatelog().getCatelog_id(),
        goods.getStock_num(),goods.getEnter_date()};
        int i = JdbcUtil.executeUpdate(sql, params);
        return i;
    }
}
