package com.develop.web.domain.users.user.service;

import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.domain.users.user.dto.MemberInfo;
import com.develop.web.domain.users.auth.mapper.AuthMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberListFetcher {
    private final AuthMapper authMapper;

    public MemberListFetcher(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    /** @description 멤버 리스트 */
    public List<MemberInfo> getMemberList(){
        return authMapper.selectMemberInfoList();
    }

    public List<MemberInfo> getMemberGetList(CriteriaDto cri) {
        return authMapper.selectMemberGetList(cri);
    }
}
