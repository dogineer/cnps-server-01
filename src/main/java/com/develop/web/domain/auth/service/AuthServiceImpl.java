package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.vo.AuthVo;
import com.develop.web.domain.auth.mapper.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthMapper authDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AuthMapper authDao, PasswordEncoder passwordEncoder) {
        this.authDao = authDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void SignUp(AuthVo authVo) {
        System.out.println("\nAuthService - SignUp");
        System.out.println(authVo.toString());

        String encodePassword = passwordEncoder.encode(authVo.getUserPassword());
        authVo.setUserpassword(encodePassword);
        authDao.insertUser(authVo);
    }

    @Override
    public AuthVo login(AuthVo authVo) {
        System.out.println("\nAuthService - login");

        AuthVo vo = authDao.selectByUser(authVo); // db 조회하고 객체 담기

        boolean isSame = passwordEncoder.matches(authVo.getUserPassword(), vo.getUserPassword());

        return null;
    }
}
