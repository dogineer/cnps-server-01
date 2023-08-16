package com.develop.web.domain.users.user.service;

import com.develop.web.domain.users.user.dto.TeamUpdateParam;
import com.develop.web.domain.users.auth.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyTeam {
    private final AuthMapper authMapper;

    /** @description 비밀번호 변경 서비스
     * */
    public void setTeam(TeamUpdateParam param) {
        authMapper.updateMemberTeamId(param);
    }
}
