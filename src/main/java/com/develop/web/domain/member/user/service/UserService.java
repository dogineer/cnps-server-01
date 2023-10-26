package com.develop.web.domain.member.user.service;

import com.develop.web.domain.member.user.dto.JoinUser;
import com.develop.web.domain.member.user.dto.PasswordChangeRequest;
import com.develop.web.domain.member.user.mapper.UserMapper;
import com.develop.web.domain.member.auth.validation.MemberChecker;
import com.develop.web.domain.member.user.dto.ProgramUpdateParam;
import com.develop.web.global.exception.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final MemberChecker memberChecker;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    /**
     * @description 회원가입
     */
    public void addUser(JoinUser user) throws CustomException {
        String newAccount = user.getAccount();

        memberChecker.accountOverlap(newAccount);
        user.encodePassword(passwordEncoder);
        userMapper.insertUser(user);

        log.info("[New UserDto] 회원가입이 완료되었습니다. 신규 계정: " + user.getAccount());
    }

    /**
     * @description 비밀번호 변경
     */
    public void modifyPassword(String account, PasswordChangeRequest request) {
        memberChecker.userPassword(account, request.getPassword());

        String changePassword = passwordEncoder.encode(request.getChangePassword());
        userMapper.updateUserPassword(account, changePassword);
    }

    /**
     * @description 프로그램 변경
     */
    public void setProgram(ProgramUpdateParam param) {
        userMapper.updateUserProgram(param);
    }
}
