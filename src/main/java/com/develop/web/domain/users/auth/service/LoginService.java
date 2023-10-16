package com.develop.web.domain.users.auth.service;

import com.develop.web.domain.users.auth.dto.LoginDto;
import com.develop.web.domain.users.auth.validation.MemberChecker;
import com.develop.web.global.exception.exception.CustomException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final MemberChecker memberChecker;

    public LoginService(MemberChecker memberChecker) {
        this.memberChecker = memberChecker;
    }

    /** @description 아이디와 비밀번호 검사 */
    public void checkAccount(LoginDto request) throws CustomException {
        String account = request.getAccount();
        String password = request.getPassword();

        memberChecker.userid(account);
        memberChecker.password(account, password);
    }
}
