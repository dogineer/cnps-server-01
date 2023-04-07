package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.vo.Access;
import com.develop.web.domain.auth.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

    public void overlap(String userid){
        User dbUserData = authMapper.selectByUserid(userid);

        if (dbUserData != null){
            log.info("아이디가 중복입니다. {}", userid);
            throw new RuntimeException("아이디 중복 발생");
        }
    }

    public void userid(String request){
        User dbUserData = authMapper.selectByUserid(request);

        if (dbUserData.getUserid() == null){
            log.info("없는 아이디 입니다. {}", request);
            throw new RuntimeException("없는 아이디 입니다.");
        }
    }

    public void password(User request){
        User repository = authMapper.selectByUserid(request.getUserid());
        boolean isSame = passwordEncoder.matches(request.getPassword(), repository.getPassword());

        if (!isSame){
            log.info("비밀번호가 맞지 않습니다.");
            throw new RuntimeException("비밀번호가 맞지 않습니다.");
        }
    }

    public User access(User request){
        User repository = authMapper.selectByUserid(request.getUserid());

        if(repository.getAccess() == Access.deny){
            log.info("접근 권한이 없습니다. {}", request.getAccess());
            throw new RuntimeException("접근 권한이 없습니다.");
        }

        return repository;
    }
}
