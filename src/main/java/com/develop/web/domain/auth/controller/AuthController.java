package com.develop.web.domain.auth.controller;

import com.develop.web.domain.auth.service.AuthService;
import com.develop.web.domain.auth.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String signUp(User userData) {
        try {
            authService.signUp(userData);
        } catch (RuntimeException e){
            return "redirect:/error";
        }
        return "redirect:/";
    }

    /*
    * 로그인
    * */
    @PostMapping("/login")
    public String login(User request, HttpSession session) throws Exception {

        String url = "redirect:/";

        try {
            User response = authService.signIn(request);

            session.setAttribute("userid", response.getUserid());
            session.setAttribute("name",   response.getName());
            session.setAttribute("role",   response.getRole());
            session.setAttribute("access", response.getAccess());

            Object userRole = session.getAttribute("role");

            if (Role.Administrator.equals(userRole)){
                System.out.println("관리자 로그인");
                url = "redirect:/Administrator";
            } else if (Role.USER.equals(userRole)){
                System.out.println("일반 유저 로그인");
                url = "redirect:/home";
            }
        } catch (RuntimeException e){
            return url;
        }
        return url;
    }

    /*
     * 비밀번호 변경
     * */
    @PostMapping("/changePassword")
    public String changePassword(PasswordChangeRequest request, HttpSession session) throws Exception {

        boolean changePasswordService = authService.changePassword(request, (String) session.getAttribute("userid"));

        if (changePasswordService){
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

    /*
     * 관리자 가입 승인
     * */
    @PostMapping("user/access/{userid}/{access}")
    public String accessCheck(
            @PathVariable String userid,
            @PathVariable String access) throws Exception {

        authService.changeAccess(userid, access);
        return "redirect:/Administrator";
    }

    /*
     * 관리자 회원 삭제
     * */
    @PostMapping("/user/delete/{userid}")
    public String userDelete(@PathVariable String userid) throws Exception {
        authService.deleteUser(userid);
        return "redirect:/Administrator";
    }
}
