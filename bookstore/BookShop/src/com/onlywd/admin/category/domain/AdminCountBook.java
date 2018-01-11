package com.onlywd.admin.category.domain;

public class AdminCountBook {
    private int count;

    @Override
    public String toString() {
        return "AdminCountBook{" +
                "count=" + count +
                '}';
    }

    public AdminCountBook(int count) {
        this.count = count;
    }

    public int getCount() {

        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AdminCountBook() {

    }
}
