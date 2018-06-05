package com.malevich.server.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

    private static final String ALLOWED_ORIGINS = "https://malevich-website.herokuapp.com";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/session/start").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/session/end").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/session /login").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/login").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/logout").allowedOrigins(ALLOWED_ORIGINS);
////        registry.addMapping("/users/**").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/users/all").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/users/id/{id}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/users/{login}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/users/type/{type}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/users/add").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/users/update").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/users/remove").allowedOrigins(ALLOWED_ORIGINS);
////        registry.addMapping("/tables/**").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/tables/all").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/tables/{id}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/tables/add").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/tables/update").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/tables/remove").allowedOrigins(ALLOWED_ORIGINS);
////        registry.addMapping("/reserved/**").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/reserved/all").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/reserved/{id}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/reserved/find").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/reserved/add").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/reserved/remove").allowedOrigins(ALLOWED_ORIGINS);
////        registry.addMapping("/menu/**").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/menu/all").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/menu/category/{category}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/menu/{id}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/menu/add").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/menu/update").allowedOrigins(ALLOWED_ORIGINS);
////        registry.addMapping("/orders/**").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/orders/all").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/orders/{id}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/orders/active").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/orders/status/{status}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/orders/table/{tableId}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/orders/date/{date}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/orders/add").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/orders/update").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/orders/remove").allowedOrigins(ALLOWED_ORIGINS);
////        registry.addMapping("/dishes/**").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/dishes/all").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/dishes/{id}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/dishes/order/{orderId}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/dishes/status/{status}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/dishes/kitchener/{kitchenerId}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/dishes/add").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/dishes/update").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/dishes/remove").allowedOrigins(ALLOWED_ORIGINS);
////        registry.addMapping("/comments/**").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/comments/all").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/comments/{id}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/comments/order/{orderId}/").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/comments/add").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/comments/update").allowedOrigins(ALLOWED_ORIGINS);
//        registry.addMapping("/comments/remove").allowedOrigins(ALLOWED_ORIGINS);
//    }
}
