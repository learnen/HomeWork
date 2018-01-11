package com.onlywd.order.dao;

import com.onlywd.order.domain.Order;
import com.onlywd.order.domain.OrderItem;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDao {

    private QueryRunner qr = new QueryRunner();


    public int insertOrder(Order order){
        Connection connection = C3P0Util.getConnection();
        String sql = "insert into orders values(?,?,?,?,?,?)";

        try {
            int update = qr.update(connection, sql, order.getOid(),
                    order.getOrdertime(), order.getTotal(), order.getState(), order.getUid(), order.getAdress());
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return 0;
    }

    public List<Order> queryOrderByUid(String uid){
        String sql = "select * from orders where uid = ?";
        Connection connection = C3P0Util.getConnection();
        try {
            List<Order> query = qr.query(connection, sql, new BeanListHandler<Order>(Order.class),uid);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return null;
    }

    public Order queryOrderByOid(String oid){
        String sql = "select * from orders where oid = ?";
        Connection connection = C3P0Util.getConnection();
        try {
            Order query = qr.query(connection, sql, new BeanHandler<Order>(Order.class),oid);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int deleteOrderByOid(String oid){
        Connection connection = C3P0Util.getConnection();
        String sql = "delete from orders where oid = ?";
        try {
            int update = qr.update(connection, sql, oid);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int updateStateByOid(String oid, int state){
        Connection connection = C3P0Util.getConnection();
        String sql = "update orders set state = ? where oid = ?";
        try {
            int update = qr.update(connection, sql, state, oid);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public List<Order> queryAllOrder(){
        Connection connection = C3P0Util.getConnection();
        String sql = "select * from orders";
        try {
            List<Order> query = qr.query(connection, sql, new BeanListHandler<Order>(Order.class));
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return null;
    }

    public void insertOrderItem(List<OrderItem> list){
        Connection connection = C3P0Util.getConnection();
        String sql = "INSERT INTO orderitem VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (OrderItem item : list) {
                preparedStatement.setString(1,item.getIid());
                preparedStatement.setInt(2,item.getCount());
                preparedStatement.setFloat(3,item.getSubtotal());
                preparedStatement.setString(4,item.getOid());
                preparedStatement.setString(5,item.getBid());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
    }

    public List<OrderItem> queryOrderitemByOid(String oid){
        String sql = "select * from orderitem where oid = ?";
        Connection connection = C3P0Util.getConnection();
        try {
            List<OrderItem> query = qr.query(connection, sql, new BeanListHandler<OrderItem>(OrderItem.class),oid);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return null;
    }

    public int deleteOrderitemByOid(String oid){
        Connection connection = C3P0Util.getConnection();
        String sql = "delete from orderitem where oid = ?";
        try {
            int update = qr.update(connection, sql, oid);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return 0;
    }
}
