package com.develop.web.domain.auth.controller;

import com.develop.web.domain.account.dto.Member;
import com.develop.web.domain.auth.dto.LoginRequest;
import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.service.Login;
import com.develop.web.domain.auth.service.UpdateAccess;
import com.develop.web.domain.auth.service.UpdateApprovedAt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    private final Login login;
    private final AuthMapper authMapper;
    private final UpdateAccess updateAccess;
    private final UpdateApprovedAt updateApprovedAt;

    public AuthController(Login login, AuthMapper authMapper, UpdateAccess updateAccess, UpdateApprovedAt updateApprovedAt) {
        this.login = login;
        this.authMapper = authMapper;
        this.updateAccess = updateAccess;
        this.updateApprovedAt = updateApprovedAt;
    }

    /**
     * @description 세션 로그인 서비스
     * @return "redirect:/ 최초 페이지로 이동"
     * */
    @PostMapping("/login")
    public String login(LoginRequest request, HttpSession session) {

        String url = "redirect:/home";

        try {
            login.getAccount(request);
            String account = request.getAccount();
            Member dbMemberInfoData = authMapper.selectMember(account);

            session.setAttribute("account", dbMemberInfoData.getAccount());
            session.setAttribute("name",    dbMemberInfoData.getName());
            session.setAttribute("rank",    dbMemberInfoData.getRankId());

            Integer rank = dbMemberInfoData.getRankId();

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
     * @description 직원 승인 서비스
     * @return "redirect:/ 관리자 페이지"
     * */
    @PostMapping("member/access/{account}")
    public String accessCheck(@PathVariable String account){
        updateAccess.setAccess(account);
        updateApprovedAt.setApprovedAt(account);

        return "redirect:/management/employee";
    }
}
