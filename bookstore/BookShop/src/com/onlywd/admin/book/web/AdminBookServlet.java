package com.onlywd.admin.book.web;

import com.onlywd.admin.book.service.AdminBookService;
import com.onlywd.book.dao.BookDao;
import com.onlywd.book.domain.Book;
import com.onlywd.category.dao.CategoryDao;
import com.onlywd.category.domain.Category;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.BaseServlet;
import util.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "AdminBookServlet",urlPatterns = "/adminBook")
public class AdminBookServlet extends BaseServlet {
    private UploadUtil uploadUtil = new UploadUtil();
    private ServletFileUpload upload = uploadUtil.getUpload();
    private List<FileItem> fileItems;

    private CategoryDao categoryDao = new CategoryDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String findAll(HttpServletRequest request, HttpServletResponse response){
        List<Book> all = AdminBookService.findAll();
        request.setAttribute("books",all);
        return "f:/adminjsps/admin/book/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response){
        String bid = request.getParameter("bid");
        Book load = AdminBookService.load(bid);
        request.setAttribute("book",load);
        List<Category> categories = categoryDao.queryAll();
        request.setAttribute("categories",categories);
        return "f:/adminjsps/admin/book/desc.jsp";
    }

    public String addPre(HttpServletRequest request, HttpServletResponse response){
        List<Category> categories = categoryDao.queryAll();
        request.setAttribute("categories",categories);
        return "f:/adminjsps/admin/book/add.jsp";
    }

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            fileItems = uploadUtil.getFileItems(request,upload);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        uploadUtil.insertBook(request,fileItems);
        uploadUtil.uploadFile(request, response,fileItems);
        String all = findAll(request, response);
        return all;
    }

    public String del(HttpServletRequest request, HttpServletResponse response){
        String bid = request.getParameter("bid");
        AdminBookService.del(bid);
        String all = findAll(request, response);
        return all;
    }

    public String mod(HttpServletRequest request, HttpServletResponse response){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Book book = new Book();
        try {
            BeanUtils.populate(book,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        AdminBookService.del(book.getBid());
        BookDao bookDao = new BookDao();
        String bid = UUID.randomUUID().toString().replaceAll("-", "");
        book.setBid(bid);
        bookDao.insert(book);
        String all = findAll(request,response);
        return all;
    }
}
