package com.java.sportshop.service;


import com.java.sportshop.pojo.Goods;
import com.java.sportshop.util.PageBean;

import java.util.List;

public interface GoodService {
    List<Goods> getTopFiveList();
    PageBean<Goods> getGoodsListByPage(String goodsName, Integer pageNo, Integer pageSize);
    Goods getGoodsDetail(Integer id);
}
