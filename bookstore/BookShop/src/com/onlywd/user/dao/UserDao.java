package com.onlywd.user.dao;

import com.onlywd.user.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.C3P0Util;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {

    private QueryRunner qr = new QueryRunner();
    private Connection connection = null;

    public User queryByUserName(String username){
        String sql = "select * from tb_user where username = ?";
        connection = C3P0Util.getConnection();
        try {
            User query = qr.query(connection,sql, new BeanHandler<User>(User.class),username);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return null;
    }

    public User queryByEmail(String email) {
        String sql = "select * from tb_user where email = ?";
        connection = C3P0Util.getConnection();
        try {
            User query = qr.query(connection,sql, new BeanHandler<User>(User.class),email);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User queryByCode(String code) {
        String sql = "select * from tb_user where code = ?";
        connection = C3P0Util.getConnection();
        try {
            User query = qr.query(connection,sql, new BeanHandler<User>(User.class),code);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(User user){
        String sql = "insertOrder into tb_user values (?,?,?,?,?,?)";
        connection = C3P0Util.getConnection();
        try {
            int i = qr.update(connection,sql, user.getUid(), user.getUsername(), user.getPassword(),
                    user.getEmail(), user.getCode(), user.getState()
            );
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }

        return 0;
    }

    public int updateState(User user){
        String sql = "update tb_user set state = 1 where code = ?";
        connection = C3P0Util.getConnection();
        try {
            int i = qr.update(connection, sql, user.getCode());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return 0;
    }
}
