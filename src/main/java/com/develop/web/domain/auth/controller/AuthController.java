package com.develop.web.domain.auth.controller;

import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.vo.AuthVo;
import com.develop.web.domain.auth.service.AuthService;
import com.develop.web.domain.utils.ScriptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(
        value = "/auth",
        method = { RequestMethod.POST, RequestMethod.GET })
public class AuthController {

    private final AuthService authService;
    private final AuthMapper authDao;

    @Autowired
    public AuthController(AuthService authService, AuthMapper authDao){
        this.authService = authService;
        this.authDao = authDao;
    }

    @GetMapping("/signUp") // 회원가입 페이지로 이동
    public String signUpForm() {
        return "auth/signUpForm";
    }

    @PostMapping("/signUp/data")
    public String signUp(AuthVo form, HttpServletResponse response, HttpServletRequest request) {
        AuthVo authVo = new AuthVo(
                form.getUserid(),
                form.getUserPassword()
        );

        AuthVo vo = authDao.selectByUser(authVo);

        if (authVo.getUserid().equals(vo.getUserid())){
            try {
                ScriptUtils.alertAndBackPage(response, "아이디가 중복입니다.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            authService.SignUp(authVo);
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(AuthVo form) {
        AuthVo authVo = new AuthVo(
                form.getUserid(),
                form.getUserPassword()
        );
        System.out.println(authService.login(authVo));
        return "redirect:/";
    }
}
