package com.onlywd.user.service.exception;

public class EmailNotMatchException extends RegisterException {
    @Override
    public String getMessage() {
        return "大哥,要你输入的是邮箱啊,格式不是很对啊!";
    }
}
