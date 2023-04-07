package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.vo.User;
import com.develop.web.domain.auth.vo.PasswordChangeRequest;
import com.develop.web.domain.auth.vo.UserLoginRequest;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AuthService {
    void signUp(User user);

    User signIn(User request) throws Exception;

    boolean changePassword(PasswordChangeRequest request, String userid);

    void changeAccess(String userid, String access);

    void deleteUser(String userid);

    List<User> memberlistAll();
}
