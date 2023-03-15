package com.develop.web.user.dao;

import com.develop.web.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    void insertUser(UserDto userDto);

}
