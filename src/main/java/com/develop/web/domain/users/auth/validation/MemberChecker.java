package com.develop.web.domain.users.auth.validation;

import com.develop.web.domain.users.token.dto.AuthMember;
import com.develop.web.domain.users.user.dto.Member;
import com.develop.web.domain.users.auth.mapper.AuthMapper;
import com.develop.web.global.exception.code.AuthErrorCode;
import com.develop.web.global.exception.code.MemberErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemberChecker implements UserDetailsService {
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberChecker(AuthMapper authMapper, PasswordEncoder passwordEncoder) {
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void overlap(String account) throws CustomException {
        Member dbMemberDataParam = authMapper.selectMember(account);

        if (dbMemberDataParam != null){
            throw new CustomException(MemberErrorCode.DUPLICATE_MEMBER);
        }
    }

    public void userid(String account) throws CustomException {
        String dbUseridData = authMapper.selectMemberAccount(account);

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

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        AuthMember user = authMapper.selectJoinedMember(account);

        if (user == null) {
            throw new CustomException(AuthErrorCode.ACCOUNT_NOT_FOUND);
        }

        return user;
    }
}
