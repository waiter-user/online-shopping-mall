package com.java.manager.dao;

import com.java.manager.pojo.User;

import java.util.List;

/**
 * 前台用户操作dao
 */
public interface UserDao {
    //查询总的记录数
    Integer selectTotalCount();
    //查询当前页的数据集合
    List<User> selectList(Integer pageNo,Integer pageSize);
}
