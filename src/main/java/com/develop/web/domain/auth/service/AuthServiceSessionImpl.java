package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.vo.AuthVo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/*
* AuthServiceSessionImpl
* */
@Service
public class AuthServiceSessionImpl implements AuthService {

    private final AuthMapper authDao;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceSessionImpl(AuthMapper authDao, PasswordEncoder passwordEncoder) {
        this.authDao = authDao;
        this.passwordEncoder = passwordEncoder;
    }

    /*
    * 회원가입 서비스
    * */
    @Override
    public boolean SignUp(AuthVo authVo, HttpServletResponse response) {
        System.out.println("\nAuthService - SignUp");

        Optional<AuthVo> vo = Optional.ofNullable(authDao.selectByUser(authVo));

        if (vo.isPresent()){
            System.out.println("아이디가 중복입니다.");
            return false;
        } else {
            String encodePassword = passwordEncoder.encode(authVo.getUserPassword());
            authVo.setUserPassword(encodePassword);
            authDao.insertUser(authVo);
            System.out.println("회원가입이 완료되었습니다");
            return true;
        }
    }

    /*
    * 로그인 서비스
    * */
    @Override
    public boolean login(AuthVo formUserData, HttpSession session) throws Exception{
        System.out.println("\nAuthService - login\n");

        AuthVo dbUserData = authDao.selectByUser(formUserData); // db 조회하고 객체 담기
        System.out.println("db 조회하고 객체 담기 = " + dbUserData);

        boolean isSame = passwordEncoder.matches(
                formUserData.getUserPassword(), dbUserData.getUserPassword());

        System.out.println("암호화 일치 = " + isSame);

        if (isSame) {
            session.setAttribute("userid", dbUserData.getUserid());
            session.setAttribute("userpassword", dbUserData.getUserPassword());
            session.setAttribute("role", dbUserData.getRole());
            return true;
        } else {
            System.out.println("다시 로그인해주세요.");
        }
        return isSame;
    }
}
