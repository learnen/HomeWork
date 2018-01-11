package com.onlywd.category.dao;

import com.onlywd.admin.category.service.exception.BookCountNotZeroException;
import com.onlywd.category.domain.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.C3P0Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryDao {

    private QueryRunner qr = new QueryRunner();

    public List<Category> queryAll(){
        String sql = "select * from category";
        Connection connection = C3P0Util.getConnection();
        try {
            List<Category> query = qr.query(connection, sql, new BeanListHandler<Category>(Category.class));
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return null;
    }

    public int insertCategory(String cid,String categoryName){
        Connection connection = C3P0Util.getConnection();
        String sql = "insert into category values (?,?)";
        try {
            int update = qr.update(connection, sql,cid, categoryName);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return 0;
    }

    public int deleteByCid(String cid) throws BookCountNotZeroException {
        Connection connection = C3P0Util.getConnection();

        String sql = "delete from category where cid = ?";
        Long count= queryBookCountByCid(cid);

            if (count != null){
                throw new BookCountNotZeroException();
            }else {
                deleteBookByCid(cid);
            }

        try {
            int update = qr.update(connection, sql, cid);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return 0;
    }

    public int deleteBookByCid(String cid){
        Connection connection = C3P0Util.getConnection();
        String sql = "delete from book where cid = ? and del = 1";
        try {
            int update = qr.update(connection, sql, cid);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public Long queryBookCountByCid(String cid){
       Connection connection = C3P0Util.getConnection();
        String sql = "SELECT count(book.cid) FROM category " +
                "  INNER JOIN book " +
                "ON category.cid = book.cid " +
                "  WHERE book.del = 0 " +
                "GROUP BY book.cid " +
                "HAVING book.cid = ?";
        try {
            Long query = qr.query(connection, sql, new ScalarHandler<>(), cid);
            return  query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }

        return 0L;
    }

    public Category queryByCid(String cid){
        Connection connection = C3P0Util.getConnection();
        String sql = "select * from category where cid = ?";
        try {
            Category query = qr.query(connection, sql, new BeanHandler<Category>(Category.class),cid);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return null;
    }

    public int update(String cid ,String cname){
        Connection connection = C3P0Util.getConnection();
        String sql = "update category set cname = ? where cid = ?";
        try {
            int update = qr.update(connection, sql, cname, cid);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Util.release(connection);
        }
        return 0;
    }

}
