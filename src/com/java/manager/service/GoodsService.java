package com.java.manager.service;

import com.java.manager.dao.GoodsDao;
import com.java.manager.pojo.Goods;
import com.java.manager.util.PageBean;

/**
 * 商品的业务接口
 */
public interface GoodsService {
//分页获取商品列表
    PageBean<Goods> queryByPage(String keyword,Integer pageNo,Integer pageSize);

    Goods getGoodsById(Integer id);
    //修改商品的方法
    Boolean updateGoods(Goods goods);
    //添加商品方法
    Boolean addGoods(Goods goods);
}
