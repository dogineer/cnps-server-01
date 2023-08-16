package com.develop.web.domain.users.user.service;

import com.develop.web.domain.users.auth.validation.MemberChecker;
import com.develop.web.domain.users.user.dto.JoinedMember;
import com.develop.web.domain.users.auth.mapper.AuthMapper;
import com.develop.web.global.exception.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateAccount {

    private final MemberChecker memberChecker;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    public void addMember(JoinedMember member) throws CustomException {
        memberChecker.overlap(member.getAccount());
        member.encodePassword(passwordEncoder);
        authMapper.insertMember(member);

        log.info("회원가입이 완료되었습니다. {}", member.getAccount());
    }
}
