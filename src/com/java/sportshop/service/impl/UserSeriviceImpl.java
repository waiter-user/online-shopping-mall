//package com.java.sportshop.service.impl;
//
//import com.java.sportshop.dao.UserDao;
//import com.java.sportshop.dao.impl.UserDaoImpl;
//import com.java.sportshop.pojo.User;
//import com.java.sportshop.service.UserService;
//
//public class UserSeriviceImpl implements UserService {
//    private UserDao userDao=new UserDaoImpl();
//    @Override
//    public User login(String name, String password) {
//        User user = userDao.selectByNameAndPwd(name, password);
//        return user;
//    }
//
//    @Override
//    public boolean updateUser(User user) {
//        return false;
//    }
//}
