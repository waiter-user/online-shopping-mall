package com.java.manager.service;

import com.java.manager.dao.UserDao;
import com.java.manager.dao.impl.UserDaoImpl;
import com.java.manager.pojo.User;
import com.java.manager.util.PageBean;

public interface UserService {
     //分页查询
    PageBean<User> queryByPage(Integer pageNo,Integer pageSize);
}
