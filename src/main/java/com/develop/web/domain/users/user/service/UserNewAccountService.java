package com.develop.web.domain.users.user.service;

import com.develop.web.domain.users.auth.validation.MemberChecker;
import com.develop.web.domain.users.user.dto.JoinedUser;
import com.develop.web.domain.users.auth.mapper.AuthMapper;
import com.develop.web.global.exception.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserNewAccountService {

    private final MemberChecker memberChecker;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    public void addUser(JoinedUser user) throws CustomException {
        String newAccount = user.getAccount();

        memberChecker.overlap(newAccount);
        user.encodePassword(passwordEncoder);
        authMapper.insertUser(user);

        log.info("[+] 회원가입이 완료되었습니다. 신규 계정: " + user.getAccount());
    }
}
