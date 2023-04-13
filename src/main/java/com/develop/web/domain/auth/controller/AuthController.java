package com.develop.web.domain.auth.controller;

import com.develop.web.domain.auth.dto.*;
import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.service.SignIn;
import com.develop.web.domain.auth.service.SignUp;
import com.develop.web.domain.member.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/*
* AuthController
* 인증을 담당하고 있는 컨트롤러
* */
@Slf4j
@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    private final SignUp signUp;
    private final SignIn signIn;
    private final AuthMapper authMapper;

    public AuthController(SignUp signUp, SignIn signIn, AuthMapper authMapper) {
        this.signIn = signIn;
        this.signUp = signUp;
        this.authMapper = authMapper;
    }

    /**
     * @description 회원가입 서비스
     * @return "redirect:/ 최초 페이지로 이동"
     * */
    @PostMapping(value = "/signup")
    public String signUp(Member member) {
        try {
            signUp.registerMember(member);
        } catch (DuplicateMemberException e){
            log.error("이미 회원가입된 아이디입니다. 다른 아이디를 입력해주세요.");
            return "redirect:/";
        }
        return "redirect:/";
    }

    /**
     * @description 세션 로그인 서비스
     * @return "redirect:/ 최초 페이지로 이동"
     * */
    @PostMapping("/signin")
    public String signIn(SignInRequest request, HttpSession session) {

        String url = "redirect:/home";

        try {
            signIn.loadAccountWithPassword(request);
            String account = request.getAccount();
            Member dbMemberInfoData = authMapper.lookupMember(account);

            session.setAttribute("account", dbMemberInfoData.getAccount());
            session.setAttribute("name",    dbMemberInfoData.getName());
            session.setAttribute("rank", dbMemberInfoData.getRank_id());

            Integer rank = dbMemberInfoData.getRank_id();

            if (12 == rank){
                System.out.println("관리자 로그인");
                url = "redirect:/management/employee";
            }
        } catch (NullPointerException e){
            log.error("서버에 아이디가 존재하지 않습니다.");
        } catch (BadCredentialsException e){
            log.error("비밀번호가 맞지 않습니다.");
            }

        return url;
    }

    /**
     * @description 로그아웃 서비스
     * @return "redirect:/ 최초 페이지로 이동"
     * */
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    /**
     * @description 비밀번호 변경 서비스
     * @return "redirect:/ 최초 페이지로 이동"
     * */
    @PostMapping("/changePassword")
    public String changePassword(PasswordChangeRequest passwordChangeRequest, HttpSession session) {
        String account = (String) session.getAttribute("account");

        try {


            session.invalidate();
            log.info("비밀번호 변경이 완료됐습니다. 다시 로그인 해주세요.");
        } catch (BadCredentialsException e) {
            log.error("비밀번호가 맞지 않음");
        }

        return "redirect:/";
    }

    /**
     * @description 직원 승인 서비스
     * @return "redirect:/ 관리자 페이지"
     * */
    @PostMapping("user/access/{account}")
    public String accessCheck(@PathVariable String account){

        return "redirect:/Administrator";
    }

    /**
     * @description 직원 삭제 서비스
     * @return "redirect:/ 관리자 페이지"
     * */
    @PostMapping("/user/delete/{account}")
    public String userDelete(@PathVariable String account){

        return "redirect:/Administrator";
    }
}
