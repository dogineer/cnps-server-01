package com.develop.web.domain.auth.controller;

import com.develop.web.domain.auth.service.AuthService;
import com.develop.web.domain.auth.vo.*;
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
    public ModelAndView signUp(User form) {
        User user = new User(
                form.getUserid(),
                form.getPassword(),
                form.getName(),
                form.getRole(),
                form.getPhone(),
                form.getEmail(),
                form.getDepartment(),
                form.getDescription()
        );

        ModelAndView mav = new ModelAndView();

        if (authService.SignUpService(user)){
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
    public ModelAndView login(User form, Model model, HttpSession session) throws Exception {
        User user = new User(
                form.getUserid(),
                form.getPassword(),
                form.getRole()
        );

        System.out.println("login form 데이터 가져오기 " + user);

        User dbUserData = authService.loginService(user);

        ModelAndView mav = new ModelAndView();

        if (dbUserData != null){
            session.setAttribute("userid", form.getUserid());
            session.setAttribute("name", dbUserData.getName());
            session.setAttribute("role", dbUserData.getRole());
            session.setAttribute("access", dbUserData.getAccess());

            boolean access = Access.allow.equals(session.getAttribute("access"));

            if (Role.Administrator.equals(session.getAttribute("role"))){
                System.out.println("관리자 로그인");
                mav.addObject("data", "관리자 로그인");
                mav.addObject("url", "/Administrator");
                mav.setViewName("etc/message");
            } else if (access){
                System.out.println("일반 유저 로그인");
                mav.addObject("data", "안녕히세요. " + session.getAttribute("userid") + "님");
                mav.addObject("url", "/home");
                mav.setViewName("etc/message");
            } else {
                System.out.println("승인되지 않은 유저");
                mav.addObject("data", "승인되지 않은 아이디입니다. \n관리자에게 문의하세요.");
                mav.addObject("url", "/");
                mav.setViewName("etc/message");
            }
        }
        return mav;
    }

    /*
     * 비밀번호 변경
     * */
    @PostMapping("/changePassword")
    public String changePassword(PasswordChangeRequest request, Model model, HttpSession session) throws Exception {

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

        authService.accessChange(userid, access);
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
