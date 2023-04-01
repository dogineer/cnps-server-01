package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.vo.User;
import com.develop.web.domain.auth.vo.PasswordChangeRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
* AuthServiceSessionImpl
* */
@Service
public class AuthServiceSessionImpl implements AuthService {

    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceSessionImpl(AuthMapper authMapper, PasswordEncoder passwordEncoder) {
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /*
    * 회원가입 서비스
    * */
    @Override
    public boolean SignUpService(User user) {
        System.out.println("\nAuthService - SignUp");

        Optional<User> vo = Optional.ofNullable(authMapper.selectByUser(user));

        if (vo.isPresent()){
            System.out.println("아이디가 중복입니다.");
            return false;
        } else {
            String encodePassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);
            authMapper.insertUser(user);
            System.out.println("회원가입이 완료되었습니다");
            return true;
        }
    }

    /*
     * 비밀번호 변경 서비스
     * */
    @Override
    public boolean changePassword(PasswordChangeRequest request, String userid) {
        System.out.println("\nAuthService - changePassword");

        User dbUserData = authMapper.selectByUserid(userid);

        boolean isSame = passwordEncoder.matches(
                request.getPassword(), dbUserData.getPassword());

        if (isSame) {
            System.out.println("비밀번호가 변경되었습니다.");
            String chagepassword = passwordEncoder.encode(request.getPasswordChangeData());
            authMapper.updatePassword(chagepassword, userid);
        }
        else {
            System.out.println("입력된 비밀번호가 맞지 않습니다.");
        }

        return isSame;
    }

    /*
    * 로그인 서비스
    * */
    @Override
    public User loginService(User formUserData) throws Exception{
        System.out.println("\nAuthService - login\n");

        User dbUserData = authMapper.selectByUser(formUserData); // db 조회하고 객체 담기
        System.out.println("db 조회하고 객체 담기 = " + dbUserData);

        boolean isSame = passwordEncoder.matches(
                formUserData.getPassword(), dbUserData.getPassword());

        if(isSame){
            return dbUserData;
        }else {
            return null;
        }
    }

    @Override
    public List<User> memberlistAll(){
        return authMapper.selectAllList();
    }

    @Override
    public void accessChange(String userid, String access){
        System.out.println("\nAuthService - accessCheck\n");

        if (access.equals("deny")){
            access = "allow";
        }

        authMapper.updateAccess(userid, access);
    }

    @Override
    public void deleteUser(String userid) {
        System.out.println("\nAuthService - deleteUser\n");

        authMapper.deleteByUser(userid);
    }
}

