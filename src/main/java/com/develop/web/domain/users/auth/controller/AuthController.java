package com.develop.web.domain.users.auth.controller;

import com.develop.web.domain.service.checker.service.ClientInfoChecker;
import com.develop.web.domain.users.auth.dto.LoginRequest;
import com.develop.web.domain.users.auth.service.LoginService;
import com.develop.web.domain.users.auth.service.TokenAuthService;
import com.develop.web.domain.users.token.dto.JwtToken;
import com.develop.web.domain.users.user.dto.JoinedUser;
import com.develop.web.domain.users.user.dto.Member;
import com.develop.web.domain.users.auth.mapper.AuthMapper;
import com.develop.web.domain.users.user.service.UserNewAccountService;
import com.develop.web.global.exception.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@Tag(name = "인증 > 로그인, 로그아웃", description = "세션과 토큰 로그인을 포함하고 있습니다.")
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {
    private final UserNewAccountService userNewAccountService;
    private final LoginService loginService;
    private final AuthMapper authMapper;
    private final ClientInfoChecker clientInfoChecker;
    private final TokenAuthService tokenAuthService;

    @PostMapping(value = "/signup")
    @Operation(summary = "회원가입", description = "회원가입에 양식의 데이터를 서버에 저장합니다.")
    public void userAccountAdd(@RequestBody JoinedUser member) throws CustomException {
        userNewAccountService.addUser(member);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "DB에 있는 유저 정보를 통해 세션을 등록합니다. (디폴트)")
    public void login(@RequestBody LoginRequest request,
                      HttpSession session,
                      HttpServletRequest httpServletRequest) {
        loginService.checkAccount(request);

        String account = request.getAccount();
        Member dbMemberInfoData = authMapper.selectMember(account);

        clientInfoChecker.clientInfo(account, httpServletRequest);

        session.setAttribute("role", dbMemberInfoData.getRole());
        session.setAttribute("empId", dbMemberInfoData.getId());
        session.setAttribute("account", dbMemberInfoData.getAccount());
        session.setAttribute("username", dbMemberInfoData.getName());
        session.setAttribute("programId", dbMemberInfoData.getProgramId());
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "세션을 삭제합니다.")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @PostMapping("/token/login")
    @Operation(summary = "로그인", description = "DB에 있는 유저 정보를 통해 토큰을 발급합니다.")
    public ResponseEntity<JwtToken> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        JwtToken token = tokenAuthService.login(loginRequest);
        clientInfoChecker.clientInfo(loginRequest.getAccount(), request);
        return ResponseEntity.ok(token);
    }
}
