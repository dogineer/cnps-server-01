package com.develop.web.domain.account.service;

import com.develop.web.domain.auth.dto.MemberInfo;
import com.develop.web.domain.auth.mapper.AuthMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindMember {
    private final AuthMapper authMapper;

    public FindMember(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    /** @description 멤버 리스트 */
    public List<MemberInfo> getMember(){
        return authMapper.selectMemberInfoList();
    }
}
