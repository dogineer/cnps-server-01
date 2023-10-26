package com.develop.web.domain.member.auth.controller;

import com.develop.web.domain.member.user.dto.UserDto;
import com.develop.web.domain.service.checker.service.ClientInfoService;
import com.develop.web.domain.member.auth.dto.LoginDto;
import com.develop.web.domain.member.auth.service.LoginService;
import com.develop.web.domain.member.token.dto.JwtToken;
import com.develop.web.domain.member.user.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@Tag(name = "인증 > 로그인, 로그아웃", description = "세션과 토큰 로그인을 포함하고 있습니다.")
@RequiredArgsConstructor
@RequestMapping(value = "/s1/api/auth")
public class AuthController {
    private final LoginService loginService;
    private final UserMapper userMapper;
    private final ClientInfoService clientInfoService;

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "DB에 있는 유저 정보를 통해 세션을 등록합니다. (디폴트)")
    public void sessionLogin(@RequestBody LoginDto loginDto, HttpServletRequest httpServletRequest) {
        loginService.checkAccount(loginDto);

        String account = loginDto.getAccount();
        UserDto dbUserInfoDataDto = userMapper.selectUser(account);
        HttpSession session = httpServletRequest.getSession(false);

        log.info("[Session] 생성된 세션 ID: " + session.getId()+"\n");
        clientInfoService.checkClientInfo(account, httpServletRequest);

        session.setAttribute("role", dbUserInfoDataDto.getRole());
        session.setAttribute("userId", dbUserInfoDataDto.getId());
        session.setAttribute("account", dbUserInfoDataDto.getAccount());
        session.setAttribute("username", dbUserInfoDataDto.getName());
        session.setAttribute("programId", dbUserInfoDataDto.getProgramId());
    }

    @GetMapping("/getSession")
    @Operation(summary = "세션 조회", description = "Http 요청을 통해 세션을 조회합니다.")
    public void sessionDetails(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);

        log.info("[Session] 현재 세션 ID: " + session.getId());
        log.info("[Session] 현재 세션 ACCOUNT: " + session.getAttribute("account")+"\n");
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "세션을 삭제합니다.")
    public void sessionLogout(HttpSession session) {
        session.invalidate();
    }

    @PostMapping("/token/login")
    @Operation(summary = "로그인", description = "DB에 있는 유저 정보를 통해 토큰을 발급합니다.")
    public ResponseEntity<JwtToken> tokenLogin(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        JwtToken token = loginService.checkAccountForToken(loginDto);
        clientInfoService.checkClientInfo(loginDto.getAccount(), request);
        return ResponseEntity.ok(token);
    }
}
