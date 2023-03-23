package com.develop.web.domain.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    /*
    * 최초 페이지
    * */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /*
    * 홈화면 페이지
    * */
    @GetMapping("/home")
    public String home() { return "home/home"; }

    /*
     * 회원가입 페이지
     * */
    @GetMapping("/auth/signup")
    public String signUpForm() {
        return "auth/signup";
    }
}
