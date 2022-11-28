package com.java.manager.service;

import com.java.manager.pojo.Admin;

/**
 * 管理员登录的业务接口
 */
public interface AdminService {
    Admin login(String username,String password);
    //修改密码
    Boolean updatePwd(Admin admin);
}
