package action;

import bean.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.UserService;

public class LoginAction extends ActionSupport {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String login(){
        ServletActionContext.getRequest().getSession().setAttribute("user",user);
        return SUCCESS;
    }

    @Override
    public void validate() {
        int login = UserService.login(user);
        System.out.println(login);
        if (login == 2){
            addFieldError("user.password","密码不正确");
        }
        if (login == 3){
            addFieldError("user.username","用户名不存在");
        }
    }
}
