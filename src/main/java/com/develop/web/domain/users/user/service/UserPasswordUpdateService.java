package com.develop.web.domain.users.user.service;

import com.develop.web.domain.users.user.dto.PasswordChangeRequest;
import com.develop.web.domain.users.auth.mapper.AuthMapper;
import com.develop.web.domain.users.auth.validation.MemberChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPasswordUpdateService {
    private final MemberChecker memberChecker;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    /** @description 비밀번호 변경 서비스
     * */
    public void modifyPassword(String account, PasswordChangeRequest request) {
        memberChecker.password(account, request.getPassword());

        String changePassword = passwordEncoder.encode(request.getChangePassword());
        authMapper.updatePassword(account, changePassword);
    }
}
