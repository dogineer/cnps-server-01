package com.develop.web.domain.member.user.service;

import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.domain.member.user.dto.Userinfo;
import com.develop.web.domain.member.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserListFetcherService {
    private final UserMapper userMapper;

    /**
     * @description 전체 멤버 조회
     */
    public List<Userinfo> findUsers() {
        return userMapper.selectMemberInfoList();
    }

    /**
     * @description 가입된 유저 리스트
     */
    public List<Userinfo> findSortUsers(CriteriaDto cri) {
        return userMapper.selectMemberGetList(cri);
    }

    /**
     * @description 삭제 플래그 유저 리스트
     */
    public List<Userinfo> findDeleteUsers(CriteriaDto cri) {
        return userMapper.selectMemberDeleteGetList(cri);
    }
}
