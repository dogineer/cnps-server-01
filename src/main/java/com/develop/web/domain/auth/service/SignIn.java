package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.dto.SignInRequest;
import org.springframework.stereotype.Service;

@Service
public class SignIn {

    private final UserChecker userChecker;

    public SignIn(UserChecker userChecker) {
        this.userChecker = userChecker;
    }

    /** @description 아이디와 비밀번호 검사 */
    public void loadAccountWithPassword(SignInRequest request) {
        userChecker.userid(request.getAccount());
        userChecker.password(request.getAccount(), request.getPassword());
    }
}
