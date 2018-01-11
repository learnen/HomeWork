package com.onlywd.user.service.exception;

public class CodeNotActiveException extends LoginException {
    @Override
    public String getMessage() {
        return "大兄弟.还是激活了再来吧,没激活,我是不会放行的啊";
    }
}
