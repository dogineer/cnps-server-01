package com.develop.web.domain.member.user.controller;

import com.develop.web.domain.member.user.dto.JoinUser;
import com.develop.web.domain.member.user.dto.PasswordChangeRequest;
import com.develop.web.domain.member.user.dto.ProgramUpdateParam;
import com.develop.web.domain.member.user.service.UserService;
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
@RequestMapping(value = "/s1/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/signup")
    @Operation(summary = "회원가입", description = "회원가입에 양식의 데이터를 서버에 저장합니다.")
    public void userAccountAdd(@RequestBody JoinUser member) throws CustomException {
        userService.addUser(member);
    }

    @PutMapping("/password/update")
    @Operation(summary = "비밀번호 변경", description = "새로운 비밀번호로 변경합니다.")
    public void userPasswordModify(@RequestBody PasswordChangeRequest passwordChangeRequest, HttpSession session) throws CustomException {
        String account = (String) session.getAttribute("account");
        userService.modifyPassword(account, passwordChangeRequest);
        session.invalidate();
    }

    @PutMapping("/program/update")
    @Operation(summary = "처음 사용자 프로그램 변경", description = "처음 사용자의 프로그램을 변경합니다.")
    public void userProgramModify(@RequestBody ProgramUpdateParam param, HttpSession session) {
        String account = (String) session.getAttribute("account");
        param.setAccount(account);
        userService.setProgram(param);
        session.invalidate();
    }
}
