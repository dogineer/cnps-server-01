package com.develop.web.domain.auth.service;

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
    public void signUp(User user) throws DuplicateMemberException {
        userChecker.overlap(user.getAccount());

        String encodePassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodePassword);
        authMapper.insertUser(user);

        log.info("회원가입이 완료되었습니다. {}", user.getAccount());
    }

    /*
    * @description 로그인 서비스
    * @param User request
    * @return User
    * */
    @Override
    public User signIn(SignInRequest request) throws AccessDeniedException {
        userChecker.userid(request.getAccount());
        userChecker.password(request.getAccount(), request.getPassword());

        return userChecker.access(request.getAccount());
    }

    /*
     * @description 비밀번호 변경 서비스
     * @param
     * */
    @Override
    public void changePassword(String account, PasswordChangeRequest request) {
        userChecker.password(account, request.getPassword());

        String changePassword = passwordEncoder.encode(request.getChangePassword());
        authMapper.updatePassword(account, changePassword);
    }

    /*
     * @description 멤버 리스트
     * */
    @Override
    public List<User> memberListAll(){
        return authMapper.selectAllList();
    }

    /*
     * @description 가입 승인
     * */
    @Override
    public void changeAccess(String account){
        authMapper.updateAccess(account);
    }

    /*
     * @description 유저 삭제
     * */
    @Override
    public void deleteUser(String account) {
        authMapper.deleteByUser(account);
    }
}

