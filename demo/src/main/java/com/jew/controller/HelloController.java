package com.jew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model, HttpSession httpSession) {

        Object obj = httpSession.getAttribute("AUTH_MENUS");
        model.addAttribute("data", "hello!!");
        return "hello";
    }

}
