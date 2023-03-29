package com.develop.web.domain.auth.mapper;

import com.develop.web.domain.auth.vo.AuthVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {

    /* 회원가입 */
    void insertUser(AuthVo authVo);

    /* 로그인 */
    AuthVo selectByUser(AuthVo authVo);

    /* 비밀번호 변경*/
    void updateByUser(AuthVo authVo);

    List<AuthVo> selectAllList();

    void updateByAccess(AuthVo vo);
}
