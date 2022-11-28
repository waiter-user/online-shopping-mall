package com.java.manager.service.impl;

import com.java.manager.dao.CatelogDao;
import com.java.manager.dao.impl.CateLogDaoImpl;
import com.java.manager.pojo.Catelog;
import com.java.manager.service.CatelogService;

import java.util.List;

public class CatelogServiceImpl implements CatelogService {
    CatelogDao catelogDao=new CateLogDaoImpl();
    @Override
    public List<Catelog> getlist() {
        //调用Dao中的方法得到商品分类列表
        List<Catelog> catelogs = catelogDao.selectAll();
        return catelogs;
    }

    @Override
    public Boolean add(Catelog catelog) {
        //调用dao中的插入方法
        int i = catelogDao.insert(catelog);
        return i>0;
    }
}
