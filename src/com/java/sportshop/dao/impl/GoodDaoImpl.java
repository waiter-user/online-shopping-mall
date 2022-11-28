package com.java.sportshop.dao.impl;

import com.java.sportshop.dao.GoodDao;
import com.java.sportshop.pojo.Goods;
import com.java.sportshop.util.JdbcUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GoodDaoImpl implements GoodDao {
    @Override
    public List<Goods> selectTopFive() {
        List<Goods> goods=new ArrayList<>();
        String sql="select goods_id,goods_name,goods_pic,mall_price from t_goods order by enter_date desc limit 5";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql);
        for(Map<String,Object> map:maps){
            Goods good=new Goods();
            int goods_id = Integer.parseInt(map.get("goods_id").toString());
            String goods_name = map.get("goods_name").toString();
            String goods_pic = map.get("goods_pic").toString();
            int mall_price = Integer.parseInt(map.get("mall_price").toString());
            good.setGoods_id(goods_id);
            good.setGoods_name(goods_name);
            good.setGoods_pic(goods_pic);
            good.setMall_price(mall_price);
            goods.add(good);
        }
        return goods;
    }

    @Override
    public Integer getTotalCount(String goodsName) {
        String sql = "select count(*) total from t_goods";
        if(goodsName!=null&&!goodsName.equals("")) {
            sql+=" where goods_name like '%"+goodsName+"%'";
        }
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql);
        Map<String,Object> map=maps.get(0);
        return Integer.valueOf(map.get("total").toString());
    }

    @Override
    public Goods selectById(Integer id) {
        String sql="select goods_id,goods_name,goods_miaoshu,market_price,mall_price,goods_pic,goods_address,enter_date from t_goods where goods_id=?";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql,id);
        Map<String,Object> map= maps.get(0);
        Goods goods=new Goods();
        goods.setGoods_id(Integer.parseInt(map.get("goods_id").toString()));
        goods.setGoods_name(map.get("goods_name").toString());
        goods.setGoods_miaoshu(map.get("goods_miaoshu").toString());
        goods.setMarket_price(Integer.parseInt(map.get("market_price").toString()));
        goods.setMall_price(Integer.parseInt(map.get("mall_price").toString()));
        goods.setGoods_address(map.get("goods_address").toString());
        goods.setGoods_pic(map.get("goods_pic").toString());
        goods.setEnter_date(java.sql.Date.valueOf(map.get("enter_date").toString()));
        return goods;
    }

    @Override
    public List<Goods> getList(String goodsName,Integer pageNo, Integer pageSize) {
        List<Goods> goods=new ArrayList<>();
//        limit只能写在最后面
        String sql="select goods_id,goods_name,market_price,goods_pic from t_goods ";
        StringBuffer sb=new StringBuffer(sql);
        if(goodsName!=null&&!goodsName.equals("")) {
            sb.append(" where goods_name like '%").append(goodsName).append("%' ");
        }
        sb.append(" limit ?,?");
//        注意空格
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sb.toString(),(pageNo-1)*pageSize,pageSize);
        for(Map<String,Object> map:maps){
            Goods good=new Goods();
            int goods_id = Integer.parseInt(map.get("goods_id").toString());
            String goods_name = map.get("goods_name").toString();
            String goods_pic = map.get("goods_pic").toString();
            int market_price = Integer.parseInt(map.get("market_price").toString());
            good.setGoods_id(goods_id);
            good.setGoods_name(goods_name);
            good.setGoods_pic(goods_pic);
            good.setMarket_price(market_price);
            goods.add(good);
        }
        return goods;
    }
}
