package com.onlywd.servlet;

import com.onlywd.bean.Book;
import com.onlywd.dao.BookDao;
import com.onlywd.dao.UserDao;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookServlet",urlPatterns = "/book")
public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int i = Integer.parseInt(request.getParameter("bid"));
        response.setContentType("text/html;charset=utf-8");
        BookDao bookDao = new BookDao();
        Book book = bookDao.queryOne(i);
        JSONObject jsonObject = JSONObject.fromObject(book);
        response.getWriter().write(jsonObject.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
