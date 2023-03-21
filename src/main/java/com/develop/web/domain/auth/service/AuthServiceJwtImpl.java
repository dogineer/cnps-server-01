package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.vo.AuthVo;
import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.utils.JwtTokenUtil;
import com.develop.web.domain.utils.ScriptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceJwtImpl implements AuthService {

    private final AuthMapper authDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthServiceJwtImpl(AuthMapper authDao, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.authDao = authDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void SignUp(AuthVo authVo, HttpServletResponse response) {
        System.out.println("\nAuthService - SignUp");

        Optional<AuthVo> vo = Optional.ofNullable(authDao.selectByUser(authVo));

        if (vo.isPresent()){
            try {
                ScriptUtils.alertAndBackPage(response, "아이디가 중복입니다.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                ScriptUtils.alertAndMovePage(response, "회원가입이 완료되었습니다", "../../");
                String encodePassword = passwordEncoder.encode(authVo.getUserPassword());
                authVo.setUserpassword(encodePassword);
                authDao.insertUser(authVo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public AuthVo login(AuthVo formUserData) {
        System.out.println("\nAuthService - login");

        AuthVo dbUserData = authDao.selectByUser(formUserData); // db 조회하고 객체 담기

        boolean isSame = passwordEncoder.matches(
                formUserData.getUserPassword(), dbUserData.getUserPassword());

        if (isSame) {
            String token = jwtTokenUtil.createToken(formUserData);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("result", token);
            System.out.println("token = "+ map);
        } else {
            System.out.println("다시 로그인해주세요.");
        }

        return null;
    }
}
