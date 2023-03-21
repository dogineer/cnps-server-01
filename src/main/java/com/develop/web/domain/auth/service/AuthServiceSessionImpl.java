package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.vo.AuthVo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class AuthServiceSessionImpl implements AuthService {

    private final AuthMapper authDao;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceSessionImpl(AuthMapper authDao, PasswordEncoder passwordEncoder) {
        this.authDao = authDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void SignUp(AuthVo authVo, HttpServletResponse response) {

    }

    @Override
    public AuthVo login(AuthVo formUserData, HttpSession session) {
        System.out.println("\nAuthService - login");

        AuthVo dbUserData = authDao.selectByUser(formUserData); // db 조회하고 객체 담기

        boolean isSame = passwordEncoder.matches(
                formUserData.getUserPassword(), dbUserData.getUserPassword());

        if (isSame) {
            session.getAttribute(formUserData.getUserid());
        } else {
            System.out.println("다시 로그인해주세요.");
        }
        return null;
    }
}
