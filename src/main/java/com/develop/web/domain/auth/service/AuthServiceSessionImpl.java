package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.auth.vo.User;
import com.develop.web.domain.auth.vo.PasswordChangeRequest;
import com.develop.web.domain.auth.vo.UserLoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
    * 회원가입 서비스
    * */
    @Override
    public void signUp(User userData) {
        log.info("AuthService - SignUp {}", userData);

        userChecker.overlap(userData.getUserid());

        String encodePassword = passwordEncoder.encode(userData.getPassword());

        userData.setPassword(encodePassword);
        authMapper.insertUser(userData);

        log.info("회원가입이 완료되었습니다. {}", userData.getUserid());
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
    public boolean signIn(UserLoginRequest request, HttpSession session) throws Exception{
        System.out.println("\nAuthService - login\n");

        User dbUserData = authMapper.selectByUserid(request.getUserid());

        boolean isSame = passwordEncoder.matches(
                request.getPassword(), dbUserData.getPassword());

        if (isSame){
            session.setAttribute("userid", dbUserData.getUserid());
            session.setAttribute("name",   dbUserData.getName());
            session.setAttribute("role",   dbUserData.getRole());
            session.setAttribute("access", dbUserData.getAccess());
        } else System.out.println("비밀번호가 맞지 않음");

        return isSame;
    }

    @Override
    public List<User> memberlistAll(){
        return authMapper.selectAllList();
    }

    @Override
    public void changeAccess(String userid, String access){
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

