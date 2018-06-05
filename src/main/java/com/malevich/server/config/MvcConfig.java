package com.malevich.server.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

    private static final String ALLOWED_ORIGINS = "https://malevich-website.herokuapp.com";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/session/**").allowedOrigins(ALLOWED_ORIGINS);
//      registry.addMapping("/session/end").allowedOrigins(ALLOWED_ORIGINS);
//      registry.addMapping("/session /login").allowedOrigins(ALLOWED_ORIGINS);
        registry.addMapping("/login").allowedOrigins(ALLOWED_ORIGINS);
        registry.addMapping("/logout").allowedOrigins(ALLOWED_ORIGINS);
        registry.addMapping("/users/**").allowedOrigins(ALLOWED_ORIGINS);
//      /users/all
//      /users/id/{id}/
//      /users/{login}/
//      /users/type/{type}/
//      /users/add
//      /users/update
//      /users/remove
        registry.addMapping("/tables/**").allowedOrigins(ALLOWED_ORIGINS);
//      /tables /all
//      /tables /{id}/
//      /tables /add
//      /tables /update
//      /tables /remove
        registry.addMapping("/reserved/**").allowedOrigins(ALLOWED_ORIGINS);
//      /reserved/all
//      /reserved/{id}/
//      /reserved/find
//      /reserved/add
//      /reserved/remove
        registry.addMapping("/menu/**").allowedOrigins(ALLOWED_ORIGINS);
//      /menu/all
//      /menu/category/{category}/
//      /menu/{id}/
//      /menu/add
//      /menu/update
        registry.addMapping("/orders/**").allowedOrigins(ALLOWED_ORIGINS);
//      /orders/all
//      /orders/{id}/
//      /orders/active
//      /orders/status /{status} /        /orders /table /{tableId} /        /orders /date /{date} /        /orders /add
//      /orders/update
//      /orders/remove
        registry.addMapping("/dishes/**").allowedOrigins(ALLOWED_ORIGINS);
//      /dishes/all
//      /dishes/{id}/
//      /dishes/order/{orderId}/
//      /dishes/status/{status}/
//      /dishes/kitchener/{kitchenerId}/
//      /dishes/add
//      /dishes/update
//      /dishes/remove
        registry.addMapping("/comments/**").allowedOrigins(ALLOWED_ORIGINS);
//      /comments/all
//      /comments/{id}/
//      /comments/order/{orderId}/
//      /comments/add
//      /comments/update
//      /commetns/remove
    }
}
