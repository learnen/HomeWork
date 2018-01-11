package com.onlywd.admin.user;

import util.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminUserServlet",urlPatterns = "/adminUser")
public class AdminUserServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String adminname = request.getParameter("adminname");
        String password = request.getParameter("password");
        if (adminname.equals("王森浩") && password.equals("222")){
            request.getSession().setAttribute("adminname",adminname);
//            response.sendRedirect("/bookshop/adminjsps/admin/index.jsp");
            return "r:/bookshop/adminjsps/admin/index.jsp";
        }else {
            request.setAttribute("msg","老哥我就不多说了,不对不对不对啊!!!!!!");
            request.setAttribute("adminname",adminname);
            request.setAttribute("password",password);
//            request.getRequestDispatcher("/adminjsps/login.jsp").forward(request,response);
            return "f:/adminjsps/login.jsp";
        }
    }
}
