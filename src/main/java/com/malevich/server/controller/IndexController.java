package com.malevich.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        model.put("message", "HowToDoInJava Reader !!");
        return "index";
    }

    @GetMapping("/info")
    public String info(Map<String, Object> model) {
        model.put("message", "HowToDoInJava Reader !!");
        return "info";
    }

    @GetMapping("/menu")
    public String menu(Map<String, Object> model) {
        model.put("message", "HowToDoInJava Reader !!");
        return "menu";
    }

    @GetMapping("/reservation")
    public String reservation(Map<String, Object> model) {
        model.put("message", "HowToDoInJava Reader !!");
        return "reservation";
    }

    @GetMapping("/profile")
    public String profile(Map<String, Object> model) {
        model.put("message", "HowToDoInJava Reader !!");
        return "profile";
    }
}
