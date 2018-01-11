package com.onlywd.book.service;

import com.onlywd.book.dao.BookDao;
import com.onlywd.book.domain.Book;

import java.util.List;

public class BookService {

   private static BookDao bookDao = new BookDao();

    public static List<Book> findAll(){
        List<Book> books = bookDao.queryAll();
        return books;
    }
    public static List<Book> findByCategory(String cid){
        List<Book> books = bookDao.queryByCid(cid);
        return books;
    }

    public static Book onload(String bid){
        Book book = bookDao.queryByBid(bid);
        return book;
    }

}
