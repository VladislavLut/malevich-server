package com.malevich.server.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Cart {

    private Map<Integer, Integer> dishes;

    public Cart() {
        dishes = new HashMap<>();
    }

    public Cart(Map <Integer, Integer > dishes){
        this.dishes = dishes;
    }

    public Map<Integer, Integer> getDishes() {
        return dishes;
    }

    public void setDishes(Map<Integer, Integer> dishes) {
        this.dishes = dishes;
    }

    public void add(Integer id) {
        dishes.putIfAbsent(id, 0);
        dishes.computeIfPresent(id, (k, v) -> v + 1);
    }

    public void remove(Integer id) {
        dishes.computeIfPresent(id, (k, v) -> v - 1);
        dishes.remove(id, 0);
    }


}
