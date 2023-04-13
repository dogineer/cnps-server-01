package com.develop.web.domain.auth.service;

import com.develop.web.domain.member.dto.Member;
import com.develop.web.domain.auth.mapper.AuthMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SignUp {
    private final UserChecker userChecker;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    public SignUp(UserChecker userChecker, PasswordEncoder passwordEncoder, AuthMapper authMapper) {
        this.userChecker = userChecker;
        this.passwordEncoder = passwordEncoder;
        this.authMapper = authMapper;
    }

    public void registerMember(Member member) throws DuplicateMemberException {
        userChecker.overlap(member.getAccount());

        String encodePassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodePassword);

        authMapper.insertMemberInfo(member);

        log.info("회원가입이 완료되었습니다. {}", member.getAccount());
    }
}
