package com.develop.web.domain.auth.mapper;

import com.develop.web.domain.auth.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {

    /* 회원가입 */
    void insertUser(User user);

    /* 로그인 */
    User selectByUser(User user);

    User selectByUserid(String userid);

    /* 비밀번호 변경*/
    void updatePassword(String chagepassword, String userid);

    List<User> selectAllList();

    void updateByAccess(User vo);

    void deleteByUser(String userid);
}
