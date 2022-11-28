package com.java.sportshop.service.impl;


import com.java.sportshop.dao.GoodDao;
import com.java.sportshop.dao.impl.GoodDaoImpl;
import com.java.sportshop.pojo.Goods;
import com.java.sportshop.service.GoodService;
import com.java.sportshop.util.PageBean;

import java.util.List;

public class GoodServiceImpl implements GoodService {
    private GoodDao goodDao=new GoodDaoImpl();
    @Override
    public List<Goods> getTopFiveList() {
        return goodDao.selectTopFive();
    }

    @Override
    public PageBean<Goods> getGoodsListByPage(String goodsName,Integer pageNo, Integer pageSize) {
        PageBean<Goods> pb=new PageBean<>();
        pb.setPageNo(pageNo);
        pb.setPageSize(pageSize);
        Integer totalCount = goodDao.getTotalCount(goodsName);
        pb.setTotalCount(totalCount);
        List<Goods> list = goodDao.getList(goodsName,pageNo, pageSize);
        pb.setData(list);
        return pb;
    }

    @Override
    public Goods getGoodsDetail(Integer id) {
        return goodDao.selectById(id);
    }
}
