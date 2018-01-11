package com.onlywd.user.web;

import com.lanou.servlet.BaseServlet;
import com.onlywd.cart.domain.Cart;
import com.onlywd.user.service.UserOperator;
import com.onlywd.user.service.exception.LoginException;
import com.onlywd.user.service.exception.RegisterException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends BaseServlet {

    private UserOperator userOperator = new UserOperator();;

    public String login(HttpServletRequest request, HttpServletResponse response){
        try {
            userOperator.login(request);
        } catch (LoginException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/jsps/user/login.jsp";
        }
        request.getSession().setAttribute("cart",new Cart());
        return "r:/jsps/main.jsp";
    }

    public String register(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        try {
            userOperator.register(request);
        } catch (RegisterException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/jsps/user/regist.jsp";
        } catch (MessagingException e) {
            System.out.println("发送文件失败");
        }
        return "r:/jsps/user/login.jsp";
    }

    public String quit(HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("user",null);
        return "r:/jsps/user/login.jsp";
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
