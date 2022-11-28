package com.java.manager.service.impl;

import com.java.manager.dao.AdminDao;
import com.java.manager.dao.impl.AdminDaoImpl;
import com.java.manager.pojo.Admin;
import com.java.manager.service.AdminService;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao= new AdminDaoImpl();
    @Override
    public Admin login(String username, String password) {
        //调用dao，返回admin对象
        Admin admin = adminDao.selectByNameAndPwd(username, password);
        return admin;
    }

    @Override
    public Boolean updatePwd(Admin admin) {
        int i = adminDao.updatePwd(admin);
        return i>0;
    }
}
