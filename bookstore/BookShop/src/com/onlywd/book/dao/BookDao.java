package com.onlywd.book.dao;

import com.onlywd.book.domain.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.C3P0Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDao {

    private QueryRunner qr = new QueryRunner();

    public List<Book> queryAll(){
        Connection connection = C3P0Util.getConnection();
        String sql= "select * from Book where del = 0";
        try {
            List<Book> query = qr.query(connection, sql, new BeanListHandler<Book>(Book.class));
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Book> queryByCid(String cid){
        Connection connection = C3P0Util.getConnection();

        String sql = "select * from book where cid = ? and del = 0";

        try {
            List<Book> query = qr.query(connection, sql, new BeanListHandler<Book>(Book.class), cid);
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

    public Book queryByBid(String bid){
        Connection connection = C3P0Util.getConnection();

        String sql = "select * from book where bid = ? and del = 0 ";

        try {
            Book query = qr.query(connection, sql, new BeanHandler<Book>(Book.class), bid);
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

    public int insert(Book book) {
        Connection connection = C3P0Util.getConnection();
        String sql = "insert into book values(?,?,?,?,?,?,0)";
        try {
            int update = qr.update(connection, sql,
                    book.getBid(),
                    book.getBname(),
                    book.getPrice(),
                    book.getAuthor(),
                    book.getImage(),
                    book.getCid());
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return 0;
    }

    public int delete(String bid){
        Connection connection = C3P0Util.getConnection();
        String sql = "update book set del = 1 where bid = ?";
        try {
            int update = qr.update(connection, sql, bid);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return 0;
    }

}
