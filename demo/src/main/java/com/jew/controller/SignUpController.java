package com.jew.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jew.service.SignUpService;
import com.jew.models.Member;

@Controller
public class SignUpController {

    @Autowired
	SignUpService signUpService;

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("data", "");
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Member member) throws Exception {
        signUpService.signup(member);
        return "redirect:/";
    }
}
