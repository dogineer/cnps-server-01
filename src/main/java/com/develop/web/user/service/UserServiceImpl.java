package com.develop.web.user.service;

import com.develop.web.user.dto.UserDto;
import com.develop.web.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void SignUp(UserDto userDto) {
        System.out.println("UserService - userSignUp");
        System.out.println(userDto.toString());

        userDao.insertUser(userDto);
    }
}
