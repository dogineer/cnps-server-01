package com.develop.web.domain.users.user.controller;

import com.develop.web.domain.users.user.dto.JoinedUser;
import com.develop.web.domain.users.user.dto.PasswordChangeRequest;
import com.develop.web.domain.users.user.dto.ProgramUpdateParam;
import com.develop.web.domain.users.user.service.UserNewAccountService;
import com.develop.web.domain.users.user.service.UserPasswordUpdateService;
import com.develop.web.domain.users.user.service.UserProgramUpdateService;
import com.develop.web.global.exception.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@Tag(name = "유저 > 개인 관리", description = "개인 사용자 인사 관리")
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final UserNewAccountService userNewAccountService;
    private final UserPasswordUpdateService userPasswordUpdateService;
    private final UserProgramUpdateService userProgramUpdateService;

    @PostMapping(value = "/signup")
    @Operation(summary = "회원가입", description = "회원가입에 양식의 데이터를 서버에 저장합니다.")
    public void userAccountAdd(@RequestBody JoinedUser member) throws CustomException {
        userNewAccountService.addUser(member);
    }

    @PutMapping("/password/update")
    @Operation(summary = "비밀번호 변경", description = "새로운 비밀번호로 변경합니다.")
    public void userPasswordModify(@RequestBody PasswordChangeRequest passwordChangeRequest, HttpSession session) throws CustomException {
        String account = (String) session.getAttribute("account");
        userPasswordUpdateService.modifyPassword(account, passwordChangeRequest);
        session.invalidate();
    }

    @PutMapping("/program/update")
    @Operation(summary = "처음 사용자 프로그램 변경", description = "처음 사용자의 프로그램을 변경합니다.")
    public void userProgramModify(@RequestBody ProgramUpdateParam param, HttpSession session) {
        String account = (String) session.getAttribute("account");
        param.setAccount(account);
        userProgramUpdateService.setProgram(param);
        session.invalidate();
    }
}
