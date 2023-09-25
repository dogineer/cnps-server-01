package com.develop.web.domain.users.user.controller;

import com.develop.web.domain.users.user.dto.JoinedMember;
import com.develop.web.domain.users.user.dto.PasswordChangeRequest;
import com.develop.web.domain.users.user.dto.TeamUpdateParam;
import com.develop.web.domain.users.user.service.CreateAccount;
import com.develop.web.domain.users.user.service.ModifyPassword;
import com.develop.web.domain.users.user.service.ModifyTeam;
import com.develop.web.global.exception.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@Tag(name = "인증 > 인사 관리", description = "사용자 권한 인사 관리")
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class MemberController {
    private final CreateAccount createAccount;
    private final ModifyPassword modifyPassword;
    private final ModifyTeam modifyTeam;

    @PostMapping(value = "/signup")
    @Operation(summary = "회원가입", description = "회원가입에 양식의 데이터를 서버에 저장합니다.")
    public void createAccount(@RequestBody JoinedMember member) throws CustomException {
        createAccount.addMember(member);
    }

    @PutMapping("/changePassword")
    @Operation(summary = "비밀번호 변경", description = "새로운 비밀번호로 변경합니다.")
    public void changePassword(PasswordChangeRequest passwordChangeRequest, HttpSession session) throws CustomException {
        String account = (String) session.getAttribute("account");
        modifyPassword.change(account, passwordChangeRequest);
        session.invalidate();
    }

    @PutMapping("/member/team/update")
    @Operation(summary = "처음 사용자 팀 변경", description = "처음 사용자의 팀을 변경합니다.")
    public void updateTeam(TeamUpdateParam param, HttpSession session) {
        String account = (String) session.getAttribute("account");
        param.setAccount(account);
        modifyTeam.setTeam(param);
        session.invalidate();
    }


}
