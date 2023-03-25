package com.develop.web.domain.auth.controller;

import com.develop.web.domain.auth.service.AuthService;
import com.develop.web.domain.auth.vo.AuthVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/*
* AuthController
* 인증을 담당하고 있는 컨트롤러
* */
@Controller
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
    public ModelAndView signUp(AuthVo form) {
        AuthVo authVo = new AuthVo(
                form.getUserid(),
                form.getPassword(),
                form.getRole());

        ModelAndView mav = new ModelAndView();

        if (authService.SignUpService(authVo)){
            mav.addObject("data", "회원가입이 완료되었습니다.");
            mav.addObject("url", "/");
            mav.setViewName("etc/message");
        } else {
            mav.addObject("data", "아이디가 중복입니다.");
            mav.addObject("url", "/auth/signup");
            mav.setViewName("etc/message");
            System.out.println("중복입니다.");
        }
        return mav;
    }

    /*
    * 로그인
    * */
    @PostMapping("/login")
    public String login(AuthVo form, Model model, HttpSession session) throws Exception {
        AuthVo authVo = new AuthVo(
                form.getUserid(),
                form.getPassword(),
                form.getRole());

        System.out.println("login form 데이터 가져오기 " + authVo);

        AuthVo authVoSerivce = authService.loginService(authVo);

        if (authVoSerivce!= null){
            session.setAttribute("userInfo", authVoSerivce);
            return "redirect:/home";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "redirect:/";
    }
}
