package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.dto.Access;
import com.develop.web.domain.auth.dto.SignInRequest;
import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.dto.User;
import com.develop.web.domain.auth.dto.PasswordChangeRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@Slf4j
public class AuthServiceSessionImpl implements AuthService {

    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserChecker userChecker;

    public AuthServiceSessionImpl(AuthMapper authMapper, PasswordEncoder passwordEncoder, UserChecker userChecker) {
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
        this.userChecker = userChecker;
    }

    /*
     * @description 회원가입 서비스
     * @param userData
     * */
    @Override
    public void signUp(User userData) throws DuplicateMemberException {
        log.info("AuthService - SignUp {}", userData);

        userChecker.overlap(userData.getUserid());

        String encodePassword = passwordEncoder.encode(userData.getPassword());

        userData.setPassword(encodePassword);
        authMapper.insertUser(userData);

        log.info("회원가입이 완료되었습니다. {}", userData.getUserid());
    }

    /*
    * @description 로그인 서비스
    * @param User request
    * @return User
    * */
    @Override
    public User signIn(SignInRequest request) throws AccessDeniedException {
        System.out.println("\nAuthService - login\n");

        userChecker.userid(request.getUserid());
        userChecker.password(request.getUserid(), request.getPassword());

        return userChecker.access(request.getUserid());
    }

    /*
     * @description 비밀번호 변경 서비스
     * @param
     * */
    @Override
    public void changePassword(String userid, PasswordChangeRequest request) {
        System.out.println("\nAuthService - changePassword");

        userChecker.password(userid, request.getPassword());

        String chagepassword = passwordEncoder.encode(request.getPasswordChangeData());
        authMapper.updatePassword(userid, chagepassword);
    }

    /*
     * @description 멤버 리스트
     * */
    @Override
    public List<User> memberlistAll(){
        return authMapper.selectAllList();
    }

    @Override
    public void changeAccess(String userid){
        System.out.println("\nAuthService - accessCheck\n");

        authMapper.updateAccess(userid, String.valueOf(Access.allow));
    }

    @Override
    public void deleteUser(String userid) {
        System.out.println("\nAuthService - deleteUser\n");

        authMapper.deleteByUser(userid);
    }
}

