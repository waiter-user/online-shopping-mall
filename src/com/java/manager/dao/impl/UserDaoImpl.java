package com.java.manager.dao.impl;

import com.java.manager.dao.UserDao;
import com.java.manager.pojo.User;
import com.java.manager.util.JdbcUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    @Override
    public Integer selectTotalCount() {
        String sql = "select count(*) total from t_user";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql);
        Map<String, Object> map = maps.get(0);
        Integer count = Integer.valueOf(map.get("total").toString());
        return count;
    }

    @Override
    public List<User> selectList(Integer pageNo, Integer pageSize) {
        String sql = "select * from t_user limit ?,?";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql, (pageNo - 1) * pageSize, pageSize);
        List<User> users = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            User user = new User();
            user.setUser_id(Integer.parseInt(map.get("user_id").toString()));
            user.setUser_name(map.get("user_name").toString());
            user.setUser_pwd(map.get("user_pwd").toString());
            user.setUser_address(map.get("user_address").toString());
            user.setUser_realname(map.get("user_realname").toString());
            user.setUser_sex(map.get("user_sex").toString());
            user.setUser_tel(map.get("user_tel").toString());
            user.setUser_email(map.get("user_email").toString());
            user.setUser_qq(map.get("user_qq").toString());
            users.add(user);
        }
        return users;
    }
}
