package com.onlywd.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
    private static String driverName;
    private static String url;
    private static String database;
    private static String user;
    private static String password;

    static {

        Properties properties = new Properties();
        ClassLoader lc = JdbcUtil.class.getClassLoader();
        InputStream is = lc.getResourceAsStream("jdbc.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driverName = properties.getProperty("DriverName");
        url = properties.getProperty("url");
        database = properties.getProperty("database");
        user = properties.getProperty("user");
        password = properties.getProperty("password");

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection(){
        try {
            Connection conn = DriverManager.getConnection(
                    url + database, user, password
            );
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
