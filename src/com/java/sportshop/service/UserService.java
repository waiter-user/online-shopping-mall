package com.java.sportshop.service;


import com.java.sportshop.pojo.User;

public interface UserService {
    User login(String username, String password);
    boolean updateUser(User user);
}
