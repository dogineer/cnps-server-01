package com.develop.web.domain.users.user.service;

import com.develop.web.domain.users.user.dto.ProgramUpdateParam;
import com.develop.web.domain.users.auth.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProgramUpdateService {
    private final AuthMapper authMapper;

    /** @description 프로그램 변경 서비스
     * */
    public void setProgram(ProgramUpdateParam param) {
        authMapper.updateMemberProgramId(param);
    }
}
