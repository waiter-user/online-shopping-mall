package com.java.sportshop.dao.impl;


import com.java.sportshop.dao.UserDao;
import com.java.sportshop.pojo.User;
import com.java.sportshop.util.JdbcUtil;

import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    @Override
    public User selectByUserNameAndPwd(String username, String password) {
        String sql="select * from t_user where user_name=? and user_pwd=?";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql, username, password);
        User user=null;
        if(maps.size()!=0){
            Map<String,Object> map=maps.get(0);
            user=new User();
            user.setUser_id(Integer.parseInt(map.get("user_id").toString()));
            user.setUser_name(username);
            user.setUser_pwd(password);
            user.setUser_address(map.get("user_address").toString());
            user.setUser_realname(map.get("user_realname").toString());
            user.setUser_sex(map.get("user_sex").toString());
            user.setUser_tel(map.get("user_tel").toString());
            user.setUser_email(map.get("user_email").toString());
            user.setUser_qq(map.get("user_qq").toString());
        }
        return user;
    }

    @Override
    public int updateUser(User user) {
        String sql="update t_user set user_name=?,user_pwd=?,user_realname=?,user_address=?,user_sex=?,user_tel=?,user_email=?,user_qq=? where user_id=?";
        return JdbcUtil.executeUpdate(sql, user.getUser_name(), user.getUser_pwd(), user.getUser_realname(), user.getUser_address(), user.getUser_sex(), user.getUser_tel(),user.getUser_email(), user.getUser_qq(), user.getUser_id());
    }

}
