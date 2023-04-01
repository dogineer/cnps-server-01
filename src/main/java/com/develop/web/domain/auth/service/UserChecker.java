package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserChecker {
    private final AuthMapper authMapper;

    public UserChecker(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    public void overlap(String userid){
        User dbUserData = authMapper.selectByUserid(userid);

        if (dbUserData != null){
            log.info("아이디가 중복입니다. {}", userid);
            throw new RuntimeException("아이디 중복 발생");
        }
    }
}
