package com.develop.web.domain.account.controller;

import com.develop.web.domain.account.dto.PasswordChangeRequest;
import com.develop.web.domain.account.service.CreateAccount;
import com.develop.web.domain.account.dto.Member;
import com.develop.web.domain.account.service.ModifyPassword;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping(value = "/auth")
public class AccountController {
    private final CreateAccount createAccount;
    private final ModifyPassword modifyPassword;

    public AccountController(CreateAccount createAccount, ModifyPassword modifyPassword) {
        this.createAccount = createAccount;
        this.modifyPassword = modifyPassword;
    }

    /**
     * @description 회원가입 서비스
     * @return "redirect:/ 최초 페이지로 이동"
     * */
    @PostMapping(value = "/signup")
    public String createAccount(Member member) {
        try {
            createAccount.addMember(member);
        } catch (DuplicateMemberException e){
            log.error("이미 회원가입된 아이디입니다. 다른 아이디를 입력해주세요.");
            return "redirect:/";
        }
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
            modifyPassword.setPassword(account, passwordChangeRequest);

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
