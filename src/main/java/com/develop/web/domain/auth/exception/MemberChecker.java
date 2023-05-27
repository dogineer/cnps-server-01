package com.develop.web.domain.auth.exception;

import com.develop.web.domain.personnel.member.dto.Member;
import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.global.exception.code.AuthErrorCode;
import com.develop.web.global.exception.code.MemberErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemberChecker {
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberChecker(AuthMapper authMapper, PasswordEncoder passwordEncoder) {
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void overlap(String account) throws CustomException {
        Member dbMemberDataParam = authMapper.selectMember(account);

        if (dbMemberDataParam != null){
            throw new CustomException(MemberErrorCode.PASSWORD_NOW_MATCH);
        }
    }

    public void userid(String account) throws CustomException {
        String dbUseridData = authMapper.selectMember(account).getAccount();

        if (dbUseridData == null){
            throw new CustomException(AuthErrorCode.ACCOUNT_NOT_FOUND);
        }
    }

    public void password(String account, String password) throws CustomException {

        String userPassword = authMapper.selectMember(account).getPassword();
        boolean isSame = passwordEncoder.matches(password, userPassword);

        if (!isSame){
            throw new CustomException(MemberErrorCode.PASSWORD_NOW_MATCH);
        }
    }
}
