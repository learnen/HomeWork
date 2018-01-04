package com.onlywd.dao;

import com.onlywd.bean.User;
import com.onlywd.utils.HelloQueryRunner;
import com.onlywd.utils.JdbcUtil;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

    private static HelloQueryRunner hr = new HelloQueryRunner();

    public static int insert(User user){
        hr = new HelloQueryRunner();
        String sql = "insert into user values(null,?,?,?)";
        try {
            int update = hr.update(JdbcUtil.getConnection(), sql,
                    user.getUsername(), user.getPassword(), user.getNickname()
            );
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static User queryByUsername(User user){
        String sql = "select * from user where username = ?";
        Connection connection = JdbcUtil.getConnection();
        try {
            User query = hr.query(connection, sql,
                    new BeanHandler<User>(User.class),user.getUsername());
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User queryByUser(User user){
        String sql = "select * from user where username = ? and password = ?";
        Connection connection = JdbcUtil.getConnection();
        try {
            User query = hr.query(connection, sql,
                    new BeanHandler<User>(User.class),user.getUsername(),
                    user.getPassword());
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> queryAll(){
        String sql = "select * from user";
        try {
            List<User> query = hr.query(JdbcUtil.getConnection(), sql, new BeanListHandler<User>(User.class));
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
