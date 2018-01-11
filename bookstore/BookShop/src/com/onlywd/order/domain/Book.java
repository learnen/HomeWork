package com.onlywd.order.domain;

import java.util.List;
import java.util.Map;

public class Book {
    private List<Map<String,BookItem>> book;

    @Override
    public String toString() {
        return "Book{" +
                "Order=" + book +
                '}';
    }

    public Book() {
    }

    public Book(List<Map<String, BookItem>> book) {
        this.book = book;
    }

    public List<Map<String, BookItem>> getBook() {
        return book;
    }

    public void setBook(List<Map<String, BookItem>> book) {
        this.book = book;
    }
}
