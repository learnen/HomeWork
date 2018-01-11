package com.onlywd.order.service.exception;

public class OrderStateWrongException extends Exception {

    @Override
    public String getMessage() {
        return "订单状态不对啊,老弟!!";
    }
}
