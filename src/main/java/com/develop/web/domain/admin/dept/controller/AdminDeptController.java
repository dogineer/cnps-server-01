package com.develop.web.domain.admin.dept.controller;

import com.develop.web.domain.admin.dept.dto.DeptDto;
import com.develop.web.domain.admin.dept.dto.PdeptDto;
import com.develop.web.domain.admin.dept.service.*;
import com.develop.web.domain.admin.dept.validation.FindDeptMemberChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "어드민 > 부서 관리", description = "")
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminDeptController {
    private final AddDeptService addDeptService;

    private final RemoveDeptService removeDeptService;

    private final FetcherDeptMidList fetcherDeptMidList;

    private final FindDeptList findDeptList;
    private final FetcherDeptHigh fetcherDeptHigh;
    private final FindDeptMemberChecker findDeptMemberChecker;
    private final FindDeptChartList findDeptChartList;

    @PostMapping("/dept/high/add")
    @Operation(summary = "본부 추가", description = "본부를 추가합니다.")
    public void addHighDept(@RequestBody PdeptDto pdeptDto) {
        log.info("본부 추가 : " + pdeptDto);
        addDeptService.insertDept(pdeptDto);
    }

    @PostMapping("/dept/mid/add")
    @Operation(summary = "부서 유형 추가", description = "부서 유형을 추가합니다.")
    public void addMidDept(@RequestBody PdeptDto pdeptDto) {
        log.info("부서 유형 추가 : " + pdeptDto);
        addDeptService.insertDept(pdeptDto);
    }

    @PostMapping("/dept/low/add")
    @Operation(summary = "부서 추가", description = "부서를 추가합니다.")
    public void addLowDept(@RequestBody PdeptDto pdeptDto) {
        log.info("부서 추가 : " + pdeptDto);
        addDeptService.insertDept(pdeptDto);
    }

    @DeleteMapping("/dept/high/delete")
    @Operation(summary = "본부 삭제", description = "본부 코드를 입력하여 본부를 삭제합니다.")
    public void deleteHighDept(Integer deptId) {
        log.info("본부 삭제 : " + deptId);
        findDeptMemberChecker.insertDeptId(deptId);
        removeDeptService.deleteDept(deptId);
    }

    @DeleteMapping("/dept/mid/delete")
    @Operation(summary = "유형 삭제", description = "본부 코드를 입력하여 본부를 삭제합니다.")
    public void deleteMidDept(Integer deptId) {
        log.info("부서 유형 삭제 : " + deptId);
        findDeptMemberChecker.insertDeptId(deptId);
        removeDeptService.deleteDept(deptId);
    }

    @DeleteMapping("/dept/low/delete")
    @Operation(summary = "부서 삭제", description = "부서 코드를 입력하여 부서를 삭제합니다.")
    public void deleteLowDept(Integer deptId) {
        log.info("부서 삭제 : " + deptId);
        findDeptMemberChecker.insertDeptId(deptId);
        removeDeptService.deleteDept(deptId);
    }

    @GetMapping("/dept/list/all")
    @Operation(summary = "전체 부서 리스트", description = "전체 부서 목록을 조회합니다.")
    public List<DeptDto> getDeptList() {
        return findDeptList.getDeptList();
    }

    @GetMapping("/dept/high/list")
    @Operation(summary = "상위 본부 리스트", description = "상위 부서 목록을 조회합니다.")
    public List<DeptDto> getTopDept() {
        return fetcherDeptHigh.getHighDept();
    }

    @GetMapping("/dept/find/type/{deptParentId}")
    @Operation(summary = "부서 유형 찾기", description = "본부의 id 값을 이용해 부서 유형을 찾습니다.")
    public List<DeptDto> getTypeDeptList(@PathVariable Integer deptParentId){
        return fetcherDeptMidList.getMidDept(deptParentId);
    }

    @GetMapping("/dept/find/tree/{deptId}")
    @Operation(summary = "부서 조직도 리스트", description = "상위 부서를 통해 하위 부서 리스트를 조회합니다.")
    public List<String> getFindNameDept(@PathVariable Integer deptId) {
        return findDeptChartList.getDeptChartList(deptId);
    }
}
