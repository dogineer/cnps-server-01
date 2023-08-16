package com.develop.web.domain.users.user.service;

import com.develop.web.domain.users.user.dto.PasswordChangeRequest;
import com.develop.web.domain.users.auth.mapper.AuthMapper;
import com.develop.web.domain.users.auth.validation.MemberChecker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ModifyPassword {
    private final MemberChecker memberChecker;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    public ModifyPassword(MemberChecker memberChecker, PasswordEncoder passwordEncoder, AuthMapper authMapper) {
        this.memberChecker = memberChecker;
        this.passwordEncoder = passwordEncoder;
        this.authMapper = authMapper;
    }

    /** @description 비밀번호 변경 서비스
     * */
    public void change(String account, PasswordChangeRequest request) {
        memberChecker.password(account, request.getPassword());

        String changePassword = passwordEncoder.encode(request.getChangePassword());
        authMapper.updatePassword(account, changePassword);
    }
}
