package com.develop.web.domain.admin.dept.controller;

import com.develop.web.domain.admin.dept.dto.DeptDto;
import com.develop.web.domain.admin.dept.dto.NewDeptDto;
import com.develop.web.domain.admin.dept.service.*;
import com.develop.web.domain.admin.dept.validation.FindDeptMemberChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "어드민 > 부서 관리", description = "")
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class DeptController {
    private final NewDept newDept;
    private final DeleteDept deleteDept;
    private final FindDeptList findDeptList;
    private final TopDeptFetcher topDeptFetcher;
    private final FindDeptMemberChecker findDeptMemberChecker;
    private final FindDeptChartList findDeptChartList;

    @PostMapping("/dept/new/")
    @Operation(summary = "부서 생성", description = "부서를 생성합니다.")
    public void newADept(@RequestBody NewDeptDto deptDto){
        newDept.setNewDept(deptDto);
    }

    @DeleteMapping("/dept/delete/{deptId}")
    @Operation(summary = "부서 삭제", description = "부서 코드를 입력하여 부서를 삭제합니다.")
    public String deleteDept(@PathVariable Integer deptId){
        findDeptMemberChecker.insertDeptId(deptId);
        deleteDept.setDeleteDept(deptId);

        return "삭제되었습니다." + deptId;
    }

    @GetMapping("/dept/list")
    @Operation(summary = "전체 부서 리스트", description = "전체 부서 목록을 조회합니다.")
    public List<DeptDto> getDeptList(){
        return findDeptList.getDeptList();
    }

    @GetMapping("/dept/top/list")
    @Operation(summary = "상위 부서 리스트", description = "상위 부서 목록을 조회합니다.")
    public List<DeptDto> getTopDept(){
        return topDeptFetcher.getTopDept();
    }

    @GetMapping("/dept/find/{deptId}")
    @Operation(summary = "부서 조직도 리스트", description = "상위 부서를 통해 하위 부서 리스트를 조회합니다.")
    public List<String> getFindNameDept(@PathVariable Integer deptId){
        return findDeptChartList.getDeptChartList(deptId);
    }
}
