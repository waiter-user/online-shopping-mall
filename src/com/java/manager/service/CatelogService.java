package com.java.manager.service;

import com.java.manager.dao.CatelogDao;
import com.java.manager.pojo.Catelog;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * 商品类别大的业务接口
 */
public interface CatelogService {
    //获取商品类别列表
    List<Catelog> getlist();
    //添加商品
    Boolean add(Catelog catelog);
}
