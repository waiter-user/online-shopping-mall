package com.java.sportshop.dao;


import com.java.sportshop.pojo.Goods;

import java.util.List;

public interface GoodDao {
    List<Goods> selectTopFive();
    Integer getTotalCount(String goodsName);
    Goods selectById(Integer id);
    List<Goods> getList(String goodsName,Integer pageNo,Integer pageSize);
}
