package com.develop.web.domain.auth.controller;

import com.develop.web.domain.personnel.member.dto.Member;
import com.develop.web.domain.auth.dto.LoginRequest;
import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.service.Login;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@Tag(name = "인증", description = "로그인 로그아웃")
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {
    private final Login login;
    private final AuthMapper authMapper;

    /**
     * @return "redirect:/ 최초 페이지로 이동"
     * @description 세션 로그인 서비스
     */
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "세션 등록")
    public String login(LoginRequest request, HttpSession session) {

        login.getAccount(request);

        String account = request.getAccount();
        Member dbMemberInfoData = authMapper.selectMember(account);

        session.setAttribute("access", dbMemberInfoData.getAccess());
        session.setAttribute("empId", dbMemberInfoData.getId());
        session.setAttribute("account", dbMemberInfoData.getAccount());
        session.setAttribute("name", dbMemberInfoData.getName());
        session.setAttribute("rank", dbMemberInfoData.getRankId());
        session.setAttribute("teamId", dbMemberInfoData.getTeamId());

        Integer rank = dbMemberInfoData.getRankId();

        if (rank == 12) {
            System.out.println("관리자 로그인");
            return "redirect:/admin/management/user";
        }
        return "redirect:/user/clip";
    }

    /**
     * @return "redirect:/ 최초 페이지로 이동"
     * @description 로그아웃 서비스
     */
    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "세션 삭제")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
