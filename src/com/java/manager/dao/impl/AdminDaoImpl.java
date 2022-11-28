package com.java.manager.dao.impl;

import com.java.manager.dao.AdminDao;
import com.java.manager.pojo.Admin;
import com.java.manager.util.JdbcUtil;

import java.util.List;
import java.util.Map;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin selectByNameAndPwd(String username, String password) {
        String sql="select * from t_admin where username=? and userpwd=?";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql, username, password);
        Admin  admin=null;
        if (maps.size()!=0) {
            //登录成功
            Map<String, Object> map = maps.get(0);
            admin=new Admin();
         admin.setUserid(Integer.valueOf(map.get("userid").toString()));
         admin.setUsername(map.get("username").toString());
         admin.setUserpwd(map.get("userpwd").toString());
        }
        return admin;
    }

    @Override
    public int updatePwd(Admin admin) {
        String sql="update t_admin set userpwd=? where userid=?";
        int i = JdbcUtil.executeUpdate(sql, admin.getUserpwd(), admin.getUserid());
        return i;
    }
}
