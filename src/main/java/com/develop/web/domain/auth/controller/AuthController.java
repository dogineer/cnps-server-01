package com.develop.web.domain.auth.controller;

import com.develop.web.domain.auth.service.AuthService;
import com.develop.web.domain.auth.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.file.AccessDeniedException;

/*
* AuthController
* 인증을 담당하고 있는 컨트롤러
* */
@Slf4j
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
    public String signUp(User user) {
        try {
            authService.signUp(user);
        } catch (DuplicateMemberException e){
            log.error("이미 회원가입된 아이디입니다. 다른 아이디를 입력해주세요.");
            return "redirect:/";
        }
        return "redirect:/";
    }

    /*
    * 로그인
    * */
    @PostMapping("/signin")
    public String signIn(SignInRequest signInRequest, HttpSession session) {

        String url = "redirect:/";

        try {
            User response = authService.signIn(signInRequest);

            session.setAttribute("account", response.getAccount());
            session.setAttribute("name", response.getName());
            session.setAttribute("role",   response.getRole());
            session.setAttribute("access",response.getAccess());

            Object userRole = session.getAttribute("role");

            if (Role.ADMIN.equals(userRole)){
                System.out.println("관리자 로그인");
                url = "redirect:/Administrator";
            } else if (Role.USER.equals(userRole)){
                System.out.println("일반 유저 로그인");
                url = "redirect:/home";
            }
        } catch (NullPointerException e){
            log.error("서버에 아이디가 존재하지 않습니다.");
        } catch (BadCredentialsException e){
            log.error("비밀번호가 맞지 않습니다.");
        } catch (AccessDeniedException e) {
            log.error("접근 권한이 없습니다. {}", session.getAttribute("access"));
        }
        return url;
    }

    /*
     * 비밀번호 변경
     * */
    @PostMapping("/changePassword")
    public String changePassword(PasswordChangeRequest passwordChangeRequest, HttpSession session) {
        String account = (String) session.getAttribute("account");

        try {
            authService.changePassword(account, passwordChangeRequest);

            session.invalidate();
            log.info("비밀번호 변경이 완료됐습니다. 다시 로그인 해주세요.");
        } catch (BadCredentialsException e) {
            log.error("비밀번호가 맞지 않음");
        }

        return "redirect:/";
    }

    /*
    * 로그아웃
    * */
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    /*
     * 관리자 가입 승인
     * */
    @PostMapping("user/access/{account}")
    public String accessCheck(@PathVariable String account){
        authService.changeAccess(account);
        return "redirect:/Administrator";
    }

    /*
     * 관리자 회원 삭제
     * */
    @PostMapping("/user/delete/{account}")
    public String userDelete(@PathVariable String account){
        authService.deleteUser(account);
        return "redirect:/Administrator";
    }
}
