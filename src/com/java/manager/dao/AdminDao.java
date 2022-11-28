package com.java.manager.dao;

import com.java.manager.pojo.Admin;

/**
 * 操作admin的类
 */

public interface AdminDao {
    //根据昵称和密码来查询管理员
    Admin selectByNameAndPwd(String username,String password);
    //修改密码
    int updatePwd(Admin admin);
}
