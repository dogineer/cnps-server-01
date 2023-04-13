package com.develop.web.domain.auth.service;

import com.develop.web.domain.member.dto.Member;
import com.develop.web.domain.auth.mapper.AuthMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Slf4j
@Component
public class UserChecker {
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    public UserChecker(AuthMapper authMapper, PasswordEncoder passwordEncoder) {
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void overlap(String account) throws DuplicateMemberException {
        Member dbMemberDataParam = authMapper.lookupMember(account);

        if (dbMemberDataParam != null){
            throw new DuplicateMemberException("아이디 중복 발생");
        }
    }

    public void userid(String account){
        String dbUseridData = authMapper.lookupMember(account).getAccount();

        if (dbUseridData == null){
            throw new UsernameNotFoundException("없는 아이디 입니다.");
        }
    }

    public void password(String account, String password) {

        String userPassword = authMapper.lookupMember(account).getPassword();
        boolean isSame = passwordEncoder.matches(password, userPassword);

        if (!isSame){
            throw new BadCredentialsException("비밀번호가 맞지 않습니다.");
        }
    }

    public Member access(String account) throws AccessDeniedException {
        Member repository = authMapper.lookupMember(account);

        if(repository.getAccess() == 0){
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }

        return repository;
    }
}
