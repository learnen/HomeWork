package user;

import Dao.UserDao;
import bean.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

public class UserAction extends ActionSupport implements ServletRequestAware{
    private HttpServletRequest request = null;
    private UserDao userDao = new UserDao();
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
    }

    public String login(){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userDao.queryByUserName(username);
        if (user != null){
            return "index";
        }else {
            request.setAttribute("msg","用户名或者密码错误!");
            return "login";
        }
    }

    public String register(){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userDao.queryByUserName("username");
        if (user == null){
            userDao.insertOne(new User(username,password));
            return "login";
        }else {
            request.setAttribute("msg","该用户名已经被注册");
            return "register";
        }
    }

}
