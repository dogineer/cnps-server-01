package com.develop.web.domain.personnel.dept.controller;

import com.develop.web.domain.personnel.dept.dto.DeptDto;
import com.develop.web.domain.personnel.dept.dto.NewDeptDto;
import com.develop.web.domain.personnel.dept.service.DeleteDept;
import com.develop.web.domain.personnel.dept.service.FindDeptList;
import com.develop.web.domain.personnel.dept.service.NewDept;
import com.develop.web.domain.personnel.dept.service.TopDeptFetcher;
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

    @PostMapping("/dept/new/")
    @Operation(summary = "부서 생성", description = "부서를 생성합니다.")
    public void newADept(@RequestBody NewDeptDto deptDto){
        newDept.setNewDept(deptDto);
    }

    @DeleteMapping("/dept/delete/{deptId}")
    @Operation(summary = "부서 삭제", description = "부서 코드록 입력하여 부서를 삭제합니다.")
    public void deleteDept(@PathVariable Integer deptId){
        deleteDept.setDeleteDept(deptId);
    }

    @GetMapping("/dept/list")
    @Operation(summary = "부서 리스트", description = "부서 목록을 조회합니다.")
    public List<DeptDto> getDeptList(){
        return findDeptList.getDeptList();
    }

    @GetMapping("/dept/top/list")
    @Operation(summary = "부서 리스트", description = "상위 부서 목록을 조회합니다.")
    public List<DeptDto> getTopDept(){
        return topDeptFetcher.getTopDept();
    }
}
