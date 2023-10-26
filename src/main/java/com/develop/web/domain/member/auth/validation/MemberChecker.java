package com.develop.web.domain.member.auth.validation;

import com.develop.web.domain.member.token.dto.UserAuthDto;
import com.develop.web.domain.member.user.dto.UserDto;
import com.develop.web.domain.member.user.mapper.UserMapper;
import com.develop.web.global.exception.code.AuthErrorCode;
import com.develop.web.global.exception.code.MemberErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberChecker implements UserDetailsService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /** @description 유저 아이디 중복 검사 */
    public void accountOverlap(String account) throws CustomException {
        UserDto dbUserDataParamDto = userMapper.selectUser(account);

        if (dbUserDataParamDto != null) {
            throw new CustomException(MemberErrorCode.DUPLICATE_MEMBER);
        }
    }

    /** @description 로그인 DB 유저 아이디 검사 */
    public void userAccount(String account) throws CustomException {
        String dbUseridData = userMapper.selectUserAccount(account);

        if (dbUseridData == null) {
            throw new CustomException(AuthErrorCode.ACCOUNT_NOT_FOUND);
        }
    }

    /** @description 로그인 DB 유저 아이디 비밀번호 검사 */
    public void userPassword(String account, String password) throws CustomException {

        String userPassword = userMapper.selectUser(account).getPassword();
        boolean isSame = passwordEncoder.matches(password, userPassword);

        if (!isSame) {
            throw new CustomException(MemberErrorCode.PASSWORD_NOW_MATCH);
        }
    }

    /** @description 토큰 유저 검사 */
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        UserAuthDto user = userMapper.selectJoinedMember(account);

        if (user == null) {
            throw new CustomException(AuthErrorCode.ACCOUNT_NOT_FOUND);
        }

        return user;
    }
}
