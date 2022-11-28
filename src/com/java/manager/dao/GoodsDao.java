package com.java.manager.dao;

import com.java.manager.pojo.Goods;
import com.java.manager.util.PageBean;

import java.util.List;

/**
 * 操作商品表的接口
 */
public interface GoodsDao {
    //查询总记录数
    Integer selectCountAll(String keyword);
    //查询当前页的数据集合
    List<Goods> selectList(String keyword,Integer pageNo,Integer pageSize);
    //根据id获取商品详情
    Goods selectById(Integer id);
    //修改商品
    int update(Goods goods);
    //插入商品
    int insert(Goods goods);
}
