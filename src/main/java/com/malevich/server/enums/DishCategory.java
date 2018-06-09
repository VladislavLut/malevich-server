package com.malevich.server.enums;

public enum DishCategory {
    SUSHI("sushi"),
    PIZZA("pizza"),
    DRINK("drink"),
    BURGER("burger"),
    SALAD("salad"),
    DESSERT("dessert"),
    PASTA("pasta"),
    SNACK("snack");

    private String name;

    DishCategory(String name) {
        this.name = name;
    }

}
