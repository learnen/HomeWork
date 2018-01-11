package com.onlywd.category.web;

import com.onlywd.category.domain.Category;
import com.onlywd.category.service.CategoryService;
import util.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet",urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String findAll(HttpServletRequest request, HttpServletResponse response){
        CategoryService categoryService = new CategoryService();
        List<Category> all = categoryService.findAll();
        request.setAttribute("result",all);
        return "f:/jsps/left.jsp";
    }


}
