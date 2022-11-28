package com.java.manager.service.impl;

import com.java.manager.dao.GoodsDao;
import com.java.manager.dao.impl.GoodsDaoImpl;
import com.java.manager.pojo.Goods;
import com.java.manager.service.GoodsService;
import com.java.manager.util.PageBean;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao=new GoodsDaoImpl();
    @Override
    public PageBean<Goods> queryByPage(String keyword, Integer pageNo, Integer pageSize) {
        //调用dao包中方法分别获取当前业数据的集合，总记录数
        PageBean<Goods> pageBean=new PageBean<>();
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(goodsDao.selectCountAll(keyword));
        pageBean.setData(goodsDao.selectList(keyword, pageNo, pageSize));
        return pageBean;
    }

    @Override
    public Goods getGoodsById(Integer id) {
        //调用dao获取商品详情
        Goods goods = goodsDao.selectById(id);
        return goods;
    }

    @Override
    public Boolean updateGoods(Goods goods) {
        int i = goodsDao.update(goods);
        return i>0;
    }

    @Override
    public Boolean addGoods(Goods goods) {
        //调用dao中添加商品的方法
        int i = goodsDao.insert(goods);
        return i>0;
    }
}
