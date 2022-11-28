package com.java.sportshop.service.impl;


import com.java.sportshop.dao.UserDao;
import com.java.sportshop.dao.impl.UserDaoImpl;
import com.java.sportshop.pojo.User;
import com.java.sportshop.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao=new UserDaoImpl();
    @Override
    public User login(String username, String password) {
        return userDao.selectByUserNameAndPwd(username, password);
    }

    @Override
    public boolean updateUser(User user) {
        int i = userDao.updateUser(user);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }
}
