package com.develop.web.domain.auth.mapper;

import com.develop.web.domain.auth.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {

    /* 회원가입 */
    void insertUser(User user);

    /* 로그인 */
    User selectByUserid(String account);

    /* 비밀번호 변경*/
    void updatePassword(String account, String changePassword);

    List<User> selectAllList();

    void updateAccess(String account);

    void deleteByUser(String account);
}
