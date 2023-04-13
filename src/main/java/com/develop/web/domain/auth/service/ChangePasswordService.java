package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.dto.PasswordChangeRequest;
import com.develop.web.domain.auth.mapper.AuthMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {
    private final UserChecker userChecker;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    public ChangePasswordService(UserChecker userChecker, PasswordEncoder passwordEncoder, AuthMapper authMapper) {
        this.userChecker = userChecker;
        this.passwordEncoder = passwordEncoder;
        this.authMapper = authMapper;
    }

    /** @description 비밀번호 변경 서비스 */
    public void changePassword(String account, PasswordChangeRequest request) {
        userChecker.password(account, request.getPassword());

        String changePassword = passwordEncoder.encode(request.getChangePassword());
        authMapper.changePassword(account, changePassword);
    }
}
