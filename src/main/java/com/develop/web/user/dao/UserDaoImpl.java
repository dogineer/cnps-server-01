package com.develop.web.user.dao;

import com.develop.web.user.dto.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    private final SqlSession session;

    public UserDaoImpl(SqlSession session) {
        this.session = session;
    }

    String nameSpace = "com.develop.web.mapper.userMapper";

    @Override
    public void insertUser(UserDto dto){
        System.out.println("UserDao - userMapper - insertUser");
        session.insert(nameSpace + "userSignUp");
    }
}
