package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.vo.User;
import com.develop.web.domain.auth.vo.PasswordChangeRequest;

import java.util.List;

public interface AuthService {
    boolean SignUpService(User user);

    boolean changePassword(PasswordChangeRequest request, String userid);

    User loginService(User user) throws Exception;

    List<User> memberlistAll();

    void accessChange(String userid, String access);

    void deleteUser(String userid);
}
