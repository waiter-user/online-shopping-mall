package com.java.manager.dao.impl;

import com.java.manager.dao.CatelogDao;
import com.java.manager.pojo.Catelog;
import com.java.manager.pojo.Goods;
import com.java.manager.util.JdbcUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CateLogDaoImpl implements CatelogDao {
    @Override
    public List<Catelog> selectAll() {
        String sql = "select * from t_catelog";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql);
        List<Catelog> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Catelog catelog = new Catelog();
            catelog.setCatelog_id(Integer.valueOf(map.get("catelog_id").toString()));
            catelog.setCatelog_name(map.get("catelog_name").toString());
            catelog.setCatelog_miaoshu(map.get("catelog_miaoshu").toString());
            list.add(catelog);
        }
        return list;
    }

    @Override
    //添加商品类别信息
    public int insert(Catelog catelog) {
        String sql = "insert into t_catelog(catelog_name,catelog_miaoshu) values(?,?) ";
        int i = JdbcUtil.executeUpdate(sql, catelog.getCatelog_name(), catelog.getCatelog_miaoshu());
        return i;
    }
}
