package com.develop.web.user.service;

import com.develop.web.user.dto.UserDto;
import com.develop.web.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void SignUp(UserDto userDto) {
        System.out.println("UserService - userSignUp");
        System.out.println(userDto.toString());

        String encodePassword = passwordEncoder.encode(userDto.getUserPassword());
        userDto.setUserpassword(encodePassword);
        userDao.insertUser(userDto);
    }
}
