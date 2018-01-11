package com.onlywd.cart.web;

import com.onlywd.book.dao.BookDao;
import com.onlywd.book.domain.Book;
import com.onlywd.cart.domain.Cart;
import com.onlywd.cart.domain.CartItem;
import util.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "CartServlet" ,urlPatterns = "/cart")
public class CartServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String add(HttpServletRequest request, HttpServletResponse response){
        Cart attribute = (Cart) request.getSession().getAttribute("cart");
        String bid = request.getParameter("bid");
        int count =Integer.parseInt(request.getParameter("count"));
        BookDao bookDao = new BookDao();
        Book book = bookDao.queryByBid(bid);

        CartItem cartItem = new CartItem(book,count);

        Map map = new HashMap();
        map.put(book.getBid(),cartItem);
        List<Map<String, CartItem>> carts = new ArrayList<>();
    if (attribute.getCarts() != null){
        carts = attribute.getCarts();
        carts.add(map);
    }else {
        carts.add(map);
    }

        Cart cart = new Cart();
        cart.setCarts(carts);

        request.getSession().setAttribute("cart",cart);

        return "f:jsps/cart/list.jsp";
    }

    public String clear(HttpServletRequest request, HttpServletResponse response){
        Cart attribute = (Cart) request.getSession().getAttribute("cart");
        attribute.clear();
        request.setAttribute("cart",attribute);
        return "f:jsps/cart/list.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response){
        Cart attribute = (Cart) request.getSession().getAttribute("cart");
        String bid = request.getParameter("bid");
        attribute.delete(bid);
        System.out.println(bid);
        request.setAttribute("cart",attribute);
        return "f:jsps/cart/list.jsp";
    }
}
