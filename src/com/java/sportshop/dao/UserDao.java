package com.java.sportshop.dao;


import com.java.sportshop.pojo.User;

public interface UserDao {
    User selectByUserNameAndPwd(String username, String password);
    int updateUser(User user);
}
