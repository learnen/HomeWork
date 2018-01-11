package com.onlywd.book.web;

import com.onlywd.book.domain.Book;
import com.onlywd.book.service.BookService;
import util.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet",urlPatterns = "/book")
public class BookServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String findAll(HttpServletRequest request, HttpServletResponse response){
        String page = request.getParameter("page");
        if (page == null){
            page = "1";
        }
        List<Book> all = BookService.findAll();
        request.setAttribute("AllBook",all);
        return "f:/jsps/book/list.jsp?page="+page;
    }
    public String findByCategory(HttpServletRequest request, HttpServletResponse response){
        String cid = request.getParameter("cid");
        String page = request.getParameter("page");
        if (page == null){
            page = "1";
        }
        List<Book> byCategory = BookService.findByCategory(cid);
        request.setAttribute("category",byCategory);
        return "f:/jsps/book/list.jsp?cid="+cid+"page="+page;
    }

    public String load(HttpServletRequest request, HttpServletResponse response){
        String bid = request.getParameter("bid");
        Book onload = BookService.onload(bid);
        request.setAttribute("onload",onload);
        return "f:/jsps/book/desc.jsp";
    }


}
