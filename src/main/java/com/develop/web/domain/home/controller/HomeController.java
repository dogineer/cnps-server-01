package com.develop.web.domain.home.controller;

import com.develop.web.domain.auth.service.AuthServiceSessionImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final AuthServiceSessionImpl authService;

    public HomeController(AuthServiceSessionImpl authService) {
        this.authService = authService;
    }

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
    public String home(HttpSession session) {
        return redirectPage("home/home", session);
    }

    /*
     * 회원가입 페이지
     * */
    @GetMapping("/auth/signup")
    public String signUpForm() {
        return "auth/signup";
    }

    public String redirectPage(String url, HttpSession session){
        if (session.getAttribute("userid") == null){
            return "redirect:/";
        }

        return url;
    }
}
