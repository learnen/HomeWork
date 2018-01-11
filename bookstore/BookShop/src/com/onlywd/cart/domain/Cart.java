package com.onlywd.cart.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart {
    private List<Map<String,CartItem>> carts;

    public Cart() {
    }

    public List<Map<String, CartItem>> getCarts() {
        return carts;
    }

    public void setCarts(List<Map<String, CartItem>> carts) {
        this.carts = carts;
    }

    public Cart(List<Map<String, CartItem>> cart) {
        this.carts = cart;
    }

    public void clear(){
        setCarts(null);
    }

    public void delete(String bid){
        List<Map<String,CartItem>> list = new ArrayList<>();
        for (Map<String, CartItem> cart : carts) {
            if (cart.get(bid) != null){
                list.add(cart);
            }
        }
        carts.remove(list.get(0));
    }
}
