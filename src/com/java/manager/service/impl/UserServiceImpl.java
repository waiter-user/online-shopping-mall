package com.java.manager.service.impl;

import com.java.manager.dao.UserDao;
import com.java.manager.dao.impl.UserDaoImpl;
import com.java.manager.pojo.User;
import com.java.manager.service.UserService;
import com.java.manager.util.PageBean;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao  userDao=new UserDaoImpl();
    @Override
    public PageBean<User> queryByPage(Integer pageNo, Integer pageSize) {
        PageBean<User> pb = new PageBean<>();
        pb.setPageNo(pageNo);
        pb.setPageSize(pageSize);
        Integer totalCount = userDao.selectTotalCount();
        pb.setTotalCount(totalCount);
        List<User> users = userDao.selectList(pageNo, pageSize);
        pb.setData(users);
        return pb;
    }
}
