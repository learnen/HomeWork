package action;

import bean.User;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDao;
import result.CodeImageResult;
import service.UserService;

public class RegisterAction extends ActionSupport {
    private User user;
    private String confirmPassword;
    private String code;
    private UserDao userDao = new UserDao();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String register(){
        userDao.insertOne(user);
        return SUCCESS;
    }

    @Override
    public void validate() {
        boolean register = UserService.register(user);
        if (!register){
         addFieldError("user.username","该用户名已被注册");
        }
        System.out.println(code);
        System.out.println(CodeImageResult.code);
        if (!code.equals(CodeImageResult.code)){
            addFieldError("code","验证码不正确");
        }
        System.out.println(confirmPassword);
        System.out.println(user.getPassword());
        if (!confirmPassword.equals(user.getPassword())){
            addFieldError("confirmPassword","请保证两次输入的密码相同");
        }
    }
}
