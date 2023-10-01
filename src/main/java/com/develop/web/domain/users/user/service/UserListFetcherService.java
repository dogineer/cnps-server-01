package com.develop.web.domain.users.user.service;

import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.domain.users.user.dto.Userinfo;
import com.develop.web.domain.users.auth.mapper.AuthMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListFetcherService {
    private final AuthMapper authMapper;

    public UserListFetcherService(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    /** @description 멤버 리스트 */
    public List<Userinfo> findUsers(){
        return authMapper.selectMemberInfoList();
    }

    public List<Userinfo> findSortUsers(CriteriaDto cri) {
        return authMapper.selectMemberGetList(cri);
    }

    public List<Userinfo> findDeleteUsers(CriteriaDto cri) {
        return authMapper.selectMemberDeleteGetList(cri);
    }
}
