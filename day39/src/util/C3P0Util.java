package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Util {

    //得到一个数据源(连接池)
    private static DataSource dataSource = new ComboPooledDataSource();

    //创建一个静态方法获得连接
    public static Connection getConnection(){
        try {
            //返回datasource.getConnection()的返回值(也就是连接)
            return dataSource.getConnection();
        } catch (SQLException e) {
            //如果出现异常,则直接停止运行,抛出异常
            throw new RuntimeException("服务器忙....");
        }

    }

    public static void release(ResultSet resultSet, Statement statement, Connection connection){
//        判断传入的参数resultSet是否为空
        if (resultSet != null){
            try {
                //如果resultSet不为空则,将其关闭
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            不管有没有关闭成功,最后将resultSet的值设为空
            resultSet = null;
        }
//        判断statement是否为空
        if (statement != null){
            try {
//                如果statement不为空,关闭该声明
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            将statement的值设为空
            statement=null;
        }

        //        判断connection是否为空
        if (connection != null){
            try {
//                如果connection不为空,关闭该声明
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            将connection的值设为空
            connection=null;
        }




    }

    public static void release(Connection connection){
//        当传如的参数只有一个Connection的时候,调用release
//        并且将resultSet与statement设置为null,使我们使用起来更加方便
        release(null,null,connection);
    }


}
