package com.onlywd.order.web;

import com.onlywd.book.dao.BookDao;
import com.onlywd.cart.domain.Cart;
import com.onlywd.cart.domain.CartItem;
import com.onlywd.order.dao.OrderDao;
import com.onlywd.order.domain.Book;
import com.onlywd.order.domain.BookItem;
import com.onlywd.order.domain.Order;
import com.onlywd.order.domain.OrderItem;
import com.onlywd.order.service.exception.OrderStateWrongException;
import com.onlywd.user.domain.User;
import util.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "OrderServlet",urlPatterns = "/order")
public class OrderServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String add(HttpServletRequest request, HttpServletResponse response){


        String uuid = UUID.randomUUID().toString().
                replaceAll("-", "");

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(d);

        int state = 1;

        String address = "";

        User user = (User)request.
                getSession().getAttribute("user");


//------------------------------------
        Cart attribute = (Cart) request.getSession().getAttribute("cart");

        List<Map<String, CartItem>> carts = attribute.getCarts();
        List<Map<String, BookItem>> books = new ArrayList<>();
        if (carts == null){
            request.setAttribute("msg","没有就不用购买了吧");
            return "f:jsps/order/msg.jsp";
        }

        float total = 0;

        BookDao bookDao = new BookDao();

        List<OrderItem> list = new ArrayList<>();
        for (Map<String, CartItem> cart : carts) {
            Map<String, BookItem> map = new HashMap<>();
            for (Map.Entry<String, CartItem> entry : cart.entrySet()) {
                BookItem bookItem = new BookItem();
                bookItem.setCount(entry.getValue().getCount());
                bookItem.setBook(entry.getValue().getBook());
                map.put(entry.getKey(), bookItem);
                float i = Float.parseFloat(entry.getValue().getBook().getPrice()) * entry.getValue().getCount() ;
                total += i;

                OrderItem orderItem = new OrderItem();
                orderItem.setIid(UUID.randomUUID().toString().
                        replaceAll("-", ""));
                orderItem.setCount(entry.getValue().getCount());
                orderItem.setSubtotal(i);
                orderItem.setOid(uuid);
                orderItem.setBid(entry.getValue().getBook().getBid());
                orderItem.setBook(entry.getValue().getBook());

                list.add(orderItem);
            }


            books.add(map);
        }

        Book book = new Book(books);


        Order order = new Order(uuid,date,total,state,user.getUid(),address);


        order.setOrderItems(list);

        request.getSession().setAttribute("cart",new Cart());
        request.setAttribute("book", order);

        OrderDao orderDao = new OrderDao();
        orderDao.insertOrder(order);

        orderDao.insertOrderItem(list);


        return "f:/jsps/order/desc.jsp";

    }


    public String myOrder(HttpServletRequest request, HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("user");
        String uid = user.getUid();
        OrderDao orderDao = new OrderDao();
        BookDao bookDao = new BookDao();
        List<Order> orders = orderDao.queryOrderByUid(uid);

        for (int i = 0; i < orders.size(); i++) {
            String oid = orders.get(i).getOid();
            List<OrderItem> list = orderDao.queryOrderitemByOid(oid);
            for (int j = 0; j < list.size(); j++) {
                String bid = list.get(j).getBid();
                com.onlywd.book.domain.Book book = bookDao.queryByBid(bid);
                list.get(j).setBook(book);
            }
            orders.get(i).setOrderItems(list);
        }


        request.setAttribute("order",orders);


        return "f:/jsps/order/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response){

        String oid = request.getParameter("oid");
        OrderDao orderDao = new OrderDao();
        Order order = orderDao.queryOrderByOid(oid);
        BookDao bookDao = new BookDao();
            List<OrderItem> list = orderDao.queryOrderitemByOid(oid);
            for (int j = 0; j < list.size(); j++) {
                String bid = list.get(j).getBid();
                com.onlywd.book.domain.Book book = bookDao.queryByBid(bid);
                list.get(j).setBook(book);
            }

            order.setOrderItems(list);
        request.setAttribute("oneOrder",order);
        return "f:/jsps/order/desc.jsp";
    }

    public String confirm(HttpServletRequest request, HttpServletResponse response)  {
        String oid = request.getParameter("oid");
        OrderDao orderDao = new OrderDao();
        Order order = orderDao.queryOrderByOid(oid);
        if (order.getState() != 3){
            try {
                throw new OrderStateWrongException();
            } catch (OrderStateWrongException e) {
                String message = e.getMessage();
                request.setAttribute("msg",message);
                return "f:/jsps/order/msg.jsp";
            }
        }else {
            //修改订单状态为4,表示买家已经签收了
            orderDao.updateStateByOid(oid,4);
//            orderDao.deleteOrderByOid(oid);
//            orderDao.deleteOrderByOid(oid);
            request.setAttribute("msg","订单确认收货成功");
            return "f:/jsps/order/msg.jsp";
        }

    }

    public String pay(HttpServletRequest request, HttpServletResponse response){
        String adress = request.getParameter("adress");
        String oid = request.getParameter("oid");
        OrderDao orderDao = new OrderDao();
        orderDao.updateStateByOid(oid,2);
        return myOrder(request,response);
    }
}
