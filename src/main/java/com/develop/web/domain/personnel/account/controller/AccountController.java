package com.develop.web.domain.personnel.account.controller;

import com.develop.web.domain.personnel.account.dto.PasswordChangeRequest;
import com.develop.web.domain.personnel.account.dto.TeamUpdateParam;
import com.develop.web.domain.personnel.account.service.CreateAccount;
import com.develop.web.domain.personnel.account.dto.Member;
import com.develop.web.domain.personnel.account.service.ModifyPassword;
import com.develop.web.domain.personnel.account.service.ModifyTeam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@Tag(name = "사용자 > 인사 관리", description = "사용자 권한 인사 관리")
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AccountController {
    private final CreateAccount createAccount;
    private final ModifyPassword modifyPassword;
    private final ModifyTeam modifyTeam;

    @PostMapping(value = "/signup")
    @Operation(summary = "회원가입", description = "회원가입에 양식의 데이터를 서버에 저장합니다.")
    public String createAccount(Member member) {
        try {
            createAccount.addMember(member);
        } catch (DuplicateMemberException e){
            log.error("이미 회원가입된 아이디입니다. 다른 아이디를 입력해주세요.");
            return "redirect:/";
        }
        return "redirect:/";
    }

    @PutMapping("/changePassword")
    @Operation(summary = "비밀번호 변경", description = "새로운 비밀번호로 변경합니다.")
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

    @PutMapping("/member/team/update")
    @Operation(summary = "처음 사용자 팀 변경", description = "처음 사용자의 팀을 변경합니다.")
    public String updateTeam(TeamUpdateParam param,  HttpSession session){
        String account = (String) session.getAttribute("account");
        param.setAccount(account);
        System.out.println(param);
        modifyTeam.setTeam(param);
        session.invalidate();
        return "redirect:/";
    }
}
