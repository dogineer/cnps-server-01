package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.dto.SignInRequest;
import com.develop.web.domain.auth.dto.User;
import com.develop.web.domain.auth.dto.PasswordChangeRequest;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface AuthService {
    void signUp(User user) throws DuplicateMemberException;

    User signIn(SignInRequest request) throws AccessDeniedException;

    void changePassword(String userid, PasswordChangeRequest request);

    void changeAccess(String userid);

    void deleteUser(String userid);

    List<User> memberlistAll();
}
