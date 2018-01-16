package Dao;

import bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.C3P0Util;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {
    private QueryRunner qr = new QueryRunner();

    public User queryByUserName(String username){
        String sql = "select * from user where username = ?";
        Connection connection = C3P0Util.getConnection();
        try {
            User query = qr.query(connection, sql, new BeanHandler<User>(User.class),username);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return null;
    }

    public int insertOne(User user){
        String sql = "insert into user values(?,?)";
        Connection connection = C3P0Util.getConnection();
        try {
            int update = qr.update(connection, sql, user.getUsername(), user.getPassword());
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
