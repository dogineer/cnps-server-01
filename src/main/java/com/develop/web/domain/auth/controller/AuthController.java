package com.develop.web.domain.auth.controller;

import com.develop.web.domain.auth.service.AuthService;
import com.develop.web.domain.auth.vo.AuthVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
* AuthController
* 인증을 담당하고 있는 컨트롤러
* */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /*
    * 회원가입 컨트롤러
    * */
    @PostMapping(value = "/signup")
    public ModelAndView signUp(AuthVo form, HttpServletResponse response) {
        AuthVo authVo = new AuthVo(
                form.getUserid(),
                form.getUserPassword(),
                form.getRole());

        if (authService.SignUp(authVo, response)){
            ModelAndView mav = new ModelAndView();
            mav.addObject("data", "회원가입이 완료되었습니다.");
            mav.setViewName("etc/message");
            return mav;
        } else {
            System.out.println("중복입니다.");
        }
        return null;
    }

    /*
    * 로그인
    * */
    @PostMapping("/login")
    public String login(AuthVo form, HttpSession session) throws Exception {
        AuthVo authVo = new AuthVo(
                form.getUserid(),
                form.getUserPassword(),
                form.getRole());

        System.out.println("login form 데이터 가져오기 " + authVo);

        if (authService.login(authVo, session)){
            System.out.println("\nAuthController - login");
            System.out.println("로그인 유저 = " + session.getAttribute("userid"));

            return "redirect:/home";
        }
        return "redirect:/";
    }
}
