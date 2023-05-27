package com.develop.web.domain.personnel.member.service;

import com.develop.web.domain.auth.exception.MemberChecker;
import com.develop.web.domain.personnel.member.dto.Member;
import com.develop.web.domain.auth.mapper.AuthMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateAccount {
    private final MemberChecker memberChecker;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    public CreateAccount(MemberChecker memberChecker, PasswordEncoder passwordEncoder, AuthMapper authMapper) {
        this.memberChecker = memberChecker;
        this.passwordEncoder = passwordEncoder;
        this.authMapper = authMapper;
    }

    public void addMember(Member member) throws DuplicateMemberException {
        memberChecker.overlap(member.getAccount());

        String encodePassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodePassword);

        authMapper.insertMember(member);

        log.info("회원가입이 완료되었습니다. {}", member.getAccount());
    }
}
