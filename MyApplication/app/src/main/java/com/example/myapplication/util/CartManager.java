package com.example.myapplication.util;

import com.example.myapplication.model.Dish;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Dish> cartList = new ArrayList<>();

    public static CartManager getInstance() {
        if (instance == null) instance = new CartManager();
        return instance;
    }

    public void addDish(Dish dish) {
        for (Dish d : cartList) {
            if (d.getName().equals(dish.getName())) {
                d.setQuantity(d.getQuantity() + 1);
                return;
            }
        }
        dish.setQuantity(1);
        cartList.add(dish);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Dish d : cartList) {
            total += d.getPrice() * d.getQuantity();
        }
        return total;
    }

    public void removeDish(Dish dish) {
        cartList.remove(dish);
    }

    public List<Dish> getCartList() { return cartList; }
    public void clearCart() { cartList.clear(); }
}