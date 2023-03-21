package com.develop.web.domain.auth.controller;

import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.vo.AuthVo;
import com.develop.web.domain.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(
        value = "/auth",
        method = { RequestMethod.POST, RequestMethod.GET })
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService, AuthMapper authDao){
        this.authService = authService;
    }

    @GetMapping("/signUp") // 회원가입 페이지로 이동
    public String signUpForm() {
        return "auth/signUpForm";
    }

    @PostMapping("/signUp/data")
    public String signUp(AuthVo form, HttpServletResponse response) {
        AuthVo authVo = new AuthVo(
                form.getUserid(),
                form.getUserPassword()
        );

        authService.SignUp(authVo, response);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(AuthVo form, HttpSession session) throws Exception {
        AuthVo authVo = new AuthVo(
                form.getUserid(),
                form.getUserPassword()
        );

        if (authService.login(authVo, session)){
            session.getAttribute("userid");
            return "redirect:/home";
        }
        return "redirect:/";
    }
}
