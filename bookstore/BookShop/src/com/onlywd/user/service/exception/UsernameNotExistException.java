package com.onlywd.user.service.exception;

public class UsernameNotExistException extends LoginException {
    @Override
    public String getMessage() {
        return "用户名都记不住的吗???,这都能输错.";
    }
}
