package com.onlywd.dao;

import com.onlywd.bean.Book;
import com.onlywd.utils.HelloQueryRunner;
import com.onlywd.utils.JdbcUtil;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BookDao {

    private HelloQueryRunner hr = new HelloQueryRunner();

    public List<Book> queryAll(){
        String sql = "select * from book";
        try {
            List<Book> query = hr.query(JdbcUtil.getConnection(),
                    sql, new BeanListHandler<Book>(Book.class));
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book queryOne(int bid){
        String sql = "select * from book where id = ?";

        try {
            Book query = hr.query(JdbcUtil.getConnection(),
                    sql, new BeanHandler<Book>(Book.class),bid);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
