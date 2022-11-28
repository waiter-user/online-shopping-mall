package com.java.manager.dao;

import com.java.manager.pojo.Catelog;
import com.java.manager.pojo.Goods;

import java.util.List;

public interface CatelogDao {
    //获取商品列表所有的记录
    List<Catelog> selectAll();
    //插入商品分类
    int insert(Catelog catelog);
    //更新数据

}
