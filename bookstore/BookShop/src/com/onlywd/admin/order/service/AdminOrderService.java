package com.onlywd.admin.order.service;

import com.onlywd.book.dao.BookDao;
import com.onlywd.book.domain.Book;
import com.onlywd.order.dao.OrderDao;
import com.onlywd.order.domain.Order;
import com.onlywd.order.domain.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class AdminOrderService {

    private static OrderDao orderDao = new OrderDao();
    private static BookDao bookDao = new BookDao();

    public static List<Order> findAll(){
        List<Order> orders = orderDao.queryAllOrder();

        for (int i = 0; i < orders.size(); i++) {
            String oid = orders.get(i).getOid();

            List<OrderItem> list = orderDao.queryOrderitemByOid(oid);
            for (int j = 0; j < list.size(); j++) {
                String bid = list.get(j).getBid();
                Book book = bookDao.queryByBid(bid);
                list.get(j).setBook(book);
            }
            orders.get(i).setOrderItems(list);
        }

        return orders;
    }

    public static List<Order> findByState(String sta){
        List<Order> orders = orderDao.queryAllOrder();

        int  state = Integer.parseInt(sta);

        List<Order> deleteList = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            String oid = orders.get(i).getOid();

            if (state != orders.get(i).getState()){
                deleteList.add(orders.get(i));
            }

            List<OrderItem> list = orderDao.queryOrderitemByOid(oid);
            for (int j = 0; j < list.size(); j++) {
                String bid = list.get(j).getBid();
                Book book = bookDao.queryByBid(bid);
                list.get(j).setBook(book);
            }
            orders.get(i).setOrderItems(list);
        }


        orders.removeAll(deleteList);


        return orders;

    }

}
