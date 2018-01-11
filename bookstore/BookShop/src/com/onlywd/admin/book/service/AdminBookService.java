package com.onlywd.admin.book.service;

import com.onlywd.book.dao.BookDao;
import com.onlywd.book.domain.Book;

import java.util.List;

public class AdminBookService {

    private static BookDao bookDao = new BookDao();

    public static List<Book> findAll(){
        List<Book> books = bookDao.queryAll();
        return books;
    }

    public static Book load(String bid){
        Book book = bookDao.queryByBid(bid);
        return book;
    }

    public static int del(String bid){
        int delete = bookDao.delete(bid);
        return delete;
    }
}
