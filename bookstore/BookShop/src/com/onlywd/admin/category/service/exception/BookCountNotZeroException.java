package com.onlywd.admin.category.service.exception;

public class BookCountNotZeroException extends Exception {
    @Override
    public String getMessage() {
        return "该类书的数量不为0,不能删除.";
    }
}
