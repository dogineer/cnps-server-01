package com.develop.web.domain.admin.dept.controller;

import com.develop.web.domain.admin.dept.dto.DeptDetailDto;
import com.develop.web.domain.admin.dept.dto.DeptPathDto;
import com.develop.web.domain.admin.dept.dto.DeptDto;
import com.develop.web.domain.admin.dept.service.*;
import com.develop.web.domain.admin.dept.validation.DeptMemberCheckerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "어드민 > 부서 관리", description = "어드민 부서 관리 api")
@RequiredArgsConstructor
@RequestMapping(value = "/s1/api/admin")
public class AdminDeptController {
    private final DeptCreationService deptCreationService;
    private final DeptDeletionService deptDeletionService;
    private final DeptListFetcherService deptListFetcherService;
    private final DeptTypeListFetcherService deptTypeListFetcherService;
    private final DeptTopFetcherService deptTopFetcherService;
    private final DeptMemberCheckerService deptMemberCheckerService;
    private final DeptPathListFetcherService deptPathListFetcherService;

    @PostMapping("/dept/add")
    @Operation(summary = "부서 추가",
        description = "부모ID가 1이면 본부, 부모ID가 본부ID일 경우 유형, 부모ID가 유형ID일 경우 부서")
    public void deptAdd(@RequestBody DeptDto deptDto) {
        deptCreationService.addDept(deptDto);
    }

    @DeleteMapping("/dept/delete/{deptId}")
    @Operation(summary = "부서 삭제", description = "deptId에 따른 제거")
    public void deptRemove(@PathVariable Integer deptId) {
        deptMemberCheckerService.findDeptId(deptId);
        deptDeletionService.deleteDept(deptId);
    }

    @GetMapping("/dept/list/all")
    @Operation(summary = "전체 부서 리스트", description = "전체 부서 목록을 조회합니다.")
    public List<DeptDetailDto> DeptList() {
        return deptListFetcherService.findDeptList();
    }

    @GetMapping("/dept/high/list")
    @Operation(summary = "상위 본부 리스트", description = "상위 부서 목록을 조회합니다.")
    public List<DeptDetailDto> DeptTopList() {
        return deptTopFetcherService.findTopDept();
    }

    @GetMapping("/dept/find/type/{deptParentId}")
    @Operation(summary = "부서 유형 찾기", description = "본부의 id 값을 이용해 부서 유형을 찾습니다.")
    public List<DeptDetailDto> DeptTypeDetails(@PathVariable Integer deptParentId){
        return deptTypeListFetcherService.findDeptType(deptParentId);
    }

    @GetMapping("/dept/find/tree/{deptId}")
    @Operation(summary = "부서 조직도 리스트", description = "상위 부서를 통해 하위 부서 리스트를 조회합니다.")
    public List<DeptPathDto> DeptTreeDetails(@PathVariable Integer deptId) {
        return deptPathListFetcherService.findDeptPathList(deptId);
    }
}
