package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public UserChecker(AuthMapper authMapper, PasswordEncoder passwordEncoder) {
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void overlap(String account) throws DuplicateMemberException {
        User dbUserData = authMapper.selectByUserid(account);

        if (dbUserData != null){
            throw new DuplicateMemberException("아이디 중복 발생");
        }
    }

    public void userid(String account){
        String dbUseridData = authMapper.selectByUserid(account).getAccount();

        if (dbUseridData == null){
            throw new UsernameNotFoundException("없는 아이디 입니다.");
        }
    }

    public void password(String account, String password) {

        String userPassword = authMapper.selectByUserid(account).getPassword();
        boolean isSame = passwordEncoder.matches(password, userPassword);

        if (!isSame){
            throw new BadCredentialsException("비밀번호가 맞지 않습니다.");
        }
    }

    public User access(String account) throws AccessDeniedException {
        User repository = authMapper.selectByUserid(account);

        if(repository.getAccess() == 0){
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }

        return repository;
    }
}
