package com.onlywd.user.service.exception;

public class PasswordNotMatchException extends LoginException {
    @Override
    public String getMessage() {
        return "大兄弟,密码记错了吧";
    }
}
