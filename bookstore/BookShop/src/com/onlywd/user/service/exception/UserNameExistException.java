package com.onlywd.user.service.exception;

public class UserNameExistException extends RegisterException {
    @Override
    public String getMessage() {
        return "用户名已存在";
    }
}
