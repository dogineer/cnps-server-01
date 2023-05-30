package com.develop.web.domain.personnel.member.controller;

import com.develop.web.domain.personnel.member.dto.PasswordChangeRequest;
import com.develop.web.domain.personnel.member.dto.TeamUpdateParam;
import com.develop.web.domain.personnel.member.service.CreateAccount;
import com.develop.web.domain.personnel.member.dto.Member;
import com.develop.web.domain.personnel.member.service.ModifyPassword;
import com.develop.web.domain.personnel.member.service.ModifyTeam;
import com.develop.web.global.exception.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@Tag(name = "사용자 > 인사 관리", description = "사용자 권한 인사 관리")
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class MemberController {
    private final CreateAccount createAccount;
    private final ModifyPassword modifyPassword;
    private final ModifyTeam modifyTeam;

    @PostMapping(value = "/signup")
    @Operation(summary = "회원가입", description = "회원가입에 양식의 데이터를 서버에 저장합니다.")
    public String createAccount(Member member) throws CustomException {

        createAccount.addMember(member);
        return "redirect:/";
    }

    @PutMapping("/changePassword")
    @Operation(summary = "비밀번호 변경", description = "새로운 비밀번호로 변경합니다.")
    public String changePassword(PasswordChangeRequest passwordChangeRequest, HttpSession session) {

        String account = (String) session.getAttribute("account");
        modifyPassword.change(account, passwordChangeRequest);
        session.invalidate();

        return "redirect:/";
    }

    @PutMapping("/member/team/update")
    @Operation(summary = "처음 사용자 팀 변경", description = "처음 사용자의 팀을 변경합니다.")
    public String updateTeam(TeamUpdateParam param, HttpSession session) {
        String account = (String) session.getAttribute("account");
        param.setAccount(account);
        modifyTeam.setTeam(param);
        session.invalidate();
        return "redirect:/";
    }
}
