package com.onlywd.user.web;

import com.onlywd.user.service.UserOperator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmailServlet",urlPatterns = "/email")
public class EmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        UserOperator userOperator = new UserOperator();
        String result = userOperator.email(code);
        request.setAttribute("msg",result);
        request.getRequestDispatcher("/jsps/msg.jsp").
                forward(request,response);
    }
}
