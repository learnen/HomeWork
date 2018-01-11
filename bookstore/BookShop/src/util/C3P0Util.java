package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by travelround on 17/4/18.
 */
public class C3P0Util {


    // 得到一个数据源(连接池)
    private static DataSource dataSource = new ComboPooledDataSource();

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        try {
            if (connection != null){
                return connection;
            }
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("服务器忙...");
        }
    }

    public static void release(ResultSet resultSet, Statement stmt, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }

    public static void release(Connection connection){
        release(null,null,connection);
    }

}
