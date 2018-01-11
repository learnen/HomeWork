package com.onlywd.admin.category.web;

import com.onlywd.admin.category.service.AdminCategoryService;
import com.onlywd.admin.category.service.exception.BookCountNotZeroException;
import com.onlywd.category.domain.Category;
import util.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCategoryServlet",urlPatterns = "/adminCategory")
public class AdminCategoryServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String findAll(HttpServletRequest request, HttpServletResponse response){
        List<Category> all = AdminCategoryService.findAll();
        request.setAttribute("category",all);
        return "f:/adminjsps/admin/category/list.jsp";
    }

    public String add(HttpServletRequest request, HttpServletResponse response){
        String cname = request.getParameter("cname");
        AdminCategoryService.add(cname);
//        request.setAttribute("category",all);
        String all = findAll(request, response);
        return all;
//        return "f:/adminjsps/admin/category/list.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response){
        String cid = request.getParameter("cid");
        int delete = 0;
        try {
            delete = AdminCategoryService.delete(cid);
            if (delete == 0){
                request.setAttribute("msg","删除失败,找不到这个分类");
                return "f:/adminjsps/admin/msg.jsp";
            }else {
                String all = findAll(request, response);
                return all;
            }
        } catch (BookCountNotZeroException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/adminjsps/admin/msg.jsp";
        }

    }

    public String editPre(HttpServletRequest request, HttpServletResponse response){
        String cid = request.getParameter("cid");
        Category oneByCid = AdminCategoryService.findOneByCid(cid);
        request.setAttribute("category",oneByCid);
        return "f:/adminjsps/admin/category/mod.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response){
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        AdminCategoryService.edit(cid,cname);
        String all = findAll(request, response);
        return all;
    }
}
