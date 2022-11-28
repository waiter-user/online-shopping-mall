package com.java.sportshop.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 *  JDBC工具类 使用Durid连接池
 */
public class JdbcUtil {
    //连接池
    private static DataSource dataSource;

    //在静态代码块中加载properties文件
    static {
        //加载druid配置文件(此文件可放在src目录下)
        InputStream ins = JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties p = new Properties();
        try {
            p.load(ins);
            dataSource = DruidDataSourceFactory.createDataSource(p); //连接池的具体实现类是DruidDataSource
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //从连接池获取数据库连接，返回Connection对象
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 通用的增删改方法
     *
     * @param sql    要执行的增删改语句
     * @param params 给SQL语句中?赋的一组值，值的类型和个数不确定
     * @return
     */
    public static int executeUpdate(String sql, Object... params) {
        int count = 0;
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            //如果sql语句中有?,通过params中的元素给?依次赋值
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JdbcUtil.closeAll(pstmt, conn);
        }
        return count;
    }

    /**
     * 通用的查询方法
     *
     * @param sql    要执行的查询语句
     * @param params 给SQL语句中?赋的一组值，值的类型和个数不确定
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement pstmt = null;
        //结果集
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            //如果sql语句中有?,通过params中的元素给?依次赋值
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            //执行SQL命令并得到结果
            rs = pstmt.executeQuery();
            //获取封装了列信息的ResultSetMetaData对象
            ResultSetMetaData md = rs.getMetaData();
            //得到列的数量
            int count = md.getColumnCount();
            while (rs.next()) {
                //用一个Map封装一行数据(1条记录)
                Map map = new LinkedHashMap();
                //循环当前行的每列
                for (int i = 1; i <= count; i++) {
                    //获取当前列的名称（如果列有别名，则得到别名）
                    String label = md.getColumnLabel(i);
                    //根据列的名称得到列的值
                    Object value = rs.getObject(label);
                    map.put(label, value);
                }
                //把map放入List集合中
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 关闭资源 (如果用了连接池，调用conn.close()是把连接归还给连接池)
     *
     * @param closeables
     */
    public static void closeAll(AutoCloseable... closeables) {
        if (null != closeables) {
            for (AutoCloseable closeable : closeables) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
