package com.onlywd.admin.order.web;

import com.onlywd.admin.order.service.AdminOrderService;
import com.onlywd.order.dao.OrderDao;
import com.onlywd.order.domain.Order;
import util.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrderServlet",urlPatterns = "/adminOrder")
public class AdminOrderServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String findAll(HttpServletRequest request, HttpServletResponse response){
        List<Order> all = AdminOrderService.findAll();
        request.setAttribute("orders",all);
        return "f:/adminjsps/admin/order/list.jsp";
    }

    public String findByState(HttpServletRequest request, HttpServletResponse response){
        String state = request.getParameter("state");
        List<Order> all = AdminOrderService.findByState(state);
        request.setAttribute("orders",all);
        return "f:/adminjsps/admin/order/list.jsp";
    }

    public String changeState(HttpServletRequest request, HttpServletResponse response){
        int state = Integer.parseInt(request.getParameter("state"));
        String oid = request.getParameter("oid");
        OrderDao orderDao = new OrderDao();
        orderDao.updateStateByOid(oid,state+1);
        return findAll(request,response);
    }
}
