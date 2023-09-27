package com.develop.web.domain.admin.dept.validation;

import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import com.develop.web.domain.users.user.dto.Member;
import com.develop.web.global.exception.code.DeptErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeptMemberCheckerService {
    private final DeptMapper deptMapper;

    public void findDeptId(Integer deptId) {
        List<Member> joinedMembers = deptMapper.selectDeptMember(deptId);

        if (!joinedMembers.isEmpty()){
            throw new CustomException(DeptErrorCode.JOIND_MEMEBER);
        }
    }
}
