package com.develop.web.domain.admin.dept.validation;

import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import com.develop.web.domain.member.user.dto.UserDto;
import com.develop.web.global.exception.code.DeptErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeptCheckerService {
    private final DeptMapper deptMapper;

    public void findDeptInMember(Integer deptId) {
        List<UserDto> joinedUserDtos = deptMapper.selectDeptMember(deptId);

        if (!joinedUserDtos.isEmpty()){
            throw new CustomException(DeptErrorCode.JOIND_MEMEBER);
        }
    }
}
