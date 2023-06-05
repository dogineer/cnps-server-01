package com.develop.web.domain.personnel.member.service;

import com.develop.web.domain.page.dto.CriteriaDto;
import com.develop.web.domain.personnel.member.dto.MemberInfo;
import com.develop.web.domain.auth.mapper.AuthMapper;
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
