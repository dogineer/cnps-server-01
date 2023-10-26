package com.develop.web.domain.member.auth.service;

import com.develop.web.domain.member.auth.dto.LoginDto;
import com.develop.web.domain.member.auth.validation.MemberChecker;
import com.develop.web.domain.member.token.dto.JwtToken;
import com.develop.web.domain.member.token.service.JwtTokenProvider;
import com.develop.web.global.exception.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberChecker memberChecker;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    /** @description 아이디와 비밀번호 검사 */
    public void checkAccount(LoginDto request) throws CustomException {
        String account = request.getAccount();
        String password = request.getPassword();

        memberChecker.userAccount(account);
        memberChecker.userPassword(account, password);
    }

    /** @description 아이디와 비밀번호 검사 후 토큰 발급 */
    public JwtToken checkAccountForToken(LoginDto loginDto) throws CustomException {
        checkAccount(loginDto);

        String account = loginDto.getAccount();
        String password = loginDto.getPassword();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);
    }
}
