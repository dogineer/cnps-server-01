package com.develop.web.domain.personnel.dept.validation;

import com.develop.web.domain.personnel.dept.mapper.DeptMapper;
import com.develop.web.domain.personnel.member.dto.Member;
import com.develop.web.global.exception.code.DeptErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindDeptMemberChecker {
    private final DeptMapper deptMapper;

    public void insertDeptId(Integer deptid) {
        List<Member> joinedMembers = deptMapper.selectDeptMembers(deptid);

        if (!joinedMembers.isEmpty()){
            throw new CustomException(DeptErrorCode.JOIND_MEMEBER);
        }
    }
}
