package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.dto.Access;
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

    public void overlap(String userid) throws DuplicateMemberException {
        User dbUserData = authMapper.selectByUserid(userid);

        if (dbUserData != null){
            log.error("아이디가 중복입니다. {}", userid);
            throw new DuplicateMemberException("아이디 중복 발생");
        }
    }

    public void userid(String request){
        String dbUseridData = authMapper.selectByUserid(request).getUserid();

        if (dbUseridData == null){
            log.error("없는 아이디 입니다. {}", request);
            throw new UsernameNotFoundException("없는 아이디 입니다.");
        }
    }

    public void password(String userid, String password) {

        String userPassword = authMapper.selectByUserid(userid).getPassword();

        boolean isSame = passwordEncoder.matches(password, userPassword);

        if (!isSame){
            log.error("비밀번호가 맞지 않습니다.");
            throw new BadCredentialsException("비밀번호가 맞지 않습니다.") {
            };
        }
    }

    public User access(String userid) throws AccessDeniedException {
        User repository = authMapper.selectByUserid(userid);

        if(repository.getAccess() == Access.deny){
            log.error("접근 권한이 없습니다. {}", repository.getAccess());
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }

        return repository;
    }
}
