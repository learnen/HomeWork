package com.onlywd.user.service.exception;

public class EmailExistException extends RegisterException {
    @Override
    public String getMessage() {
        return "不好意思,邮箱已经被注册了啊";
    }
}
