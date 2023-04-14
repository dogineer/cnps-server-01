package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.dto.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class Login {

    private final MemberChecker memberChecker;

    public Login(MemberChecker memberChecker) {
        this.memberChecker = memberChecker;
    }

    /** @description 아이디와 비밀번호 검사 */
    public void getAccount(LoginRequest request) {
        String account = request.getAccount();
        String password = request.getPassword();

        memberChecker.userid(account);
        memberChecker.password(account, password);
    }
}
