package com.onlywd.web;

import com.onlywd.bean.User;
import com.onlywd.dao.UserDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet (name = "login",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")){
                resp.getWriter().write(cookie.getValue());
            }else {
                resp.getWriter().write("");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        req.setCharacterEncoding("utf-8");
        Map<String, String[]> map = req.getParameterMap();

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User one = UserDao.queryByUser(user);
        if (one != null){
            HttpSession session = req.getSession();
            session.setAttribute("user",one);

            Cookie cookie  = new Cookie("user",one.getUsername());
            cookie.setPath("/Day29/login");
            cookie.setMaxAge(60*60*24*30);
            resp.addCookie(cookie);

            resp.sendRedirect("http://localhost:8080/Day29/index.jsp");
        }else {
            resp.sendRedirect("http://localhost:8080/Day29/html/login.html");
        }

    }
}
