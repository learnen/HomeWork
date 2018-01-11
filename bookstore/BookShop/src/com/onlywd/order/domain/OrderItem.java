package com.onlywd.order.domain;

import com.onlywd.book.domain.Book;

public class OrderItem {
    private String iid;
    private int count;
    private float subtotal;
    private String oid;
    private String bid;

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public OrderItem() {
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "iid='" + iid + '\'' +
                ", count=" + count +
                ", subtotal=" + subtotal +
                ", oid='" + oid + '\'' +
                ", bid='" + bid + '\'' +
                '}';
    }

    public OrderItem(String iid, int count, float subtotal, String oid, String bid) {
        this.iid = iid;
        this.count = count;
        this.subtotal = subtotal;
        this.oid = oid;
        this.bid = bid;
    }
}
