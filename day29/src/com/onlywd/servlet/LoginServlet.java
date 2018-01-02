package com.onlywd.servlet;

import com.onlywd.bean.User;
import com.onlywd.dao.UserDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet (name = "login",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        req.setCharacterEncoding("utf-8");
        Map<String, String[]> map = req.getParameterMap();

        try {
            BeanUtils.populate(user,map);
            System.out.println(user.getUsername());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User one = UserDao.queryTwo(user);
        if (one != null){
            getServletContext().setAttribute("user",one);
            resp.sendRedirect("http://localhost:8080/index.jsp");
        }else {
            resp.sendRedirect("http://localhost:8080/html/login.html");
        }

    }
}
