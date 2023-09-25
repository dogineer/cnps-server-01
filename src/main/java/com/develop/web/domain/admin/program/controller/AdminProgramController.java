package com.develop.web.domain.admin.program.controller;

import com.develop.web.domain.admin.program.dto.ProgramRecursionDto;
import com.develop.web.domain.admin.program.dto.ProgramDto;
import com.develop.web.domain.admin.program.dto.ProgramPathDto;
import com.develop.web.domain.admin.program.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "어드민 > 프로그램&팀 관리", description = "유형 > 프로그램 > 팀")
@RequiredArgsConstructor
@RequestMapping(value = "/admin/program")
public class AdminProgramController {
    private final AddProgramService addProgramService;
    private final DeleteProgramService deleteProgramService;
    private final FetchProgramRecursionList fetchProgramRecursionList;
    private final FetchProgramTypeList fetchProgramTypeList;
    private final FetchProgramPathList fetchProgramPathList;
    private final FindProgramService findProgramService;

    @PostMapping("/add")
    @Operation(summary = "프로그램, 유형, 팀 추가하기",
        description = "부모ID가 1이면 유형, 부모ID가 유형ID일 경우 프로그램")
    public void addProgram(@RequestBody ProgramDto programDto) {
        addProgramService.insertProgram(programDto);
    }

    @DeleteMapping("/delete/{programId}")
    @Operation(summary = "프로그램 삭제하기", description = "프로그램을 삭제합니다.")
    public void deleteProgram(@PathVariable("programId") Integer programId) {
        deleteProgramService.deleteProgram(programId);
    }

    @GetMapping("/list/all")
    @Operation(summary = "전체 팀 리스트", description = "전체 팀 목록을 조회합니다.")
    public List<ProgramRecursionDto> getProgramList() {
        return fetchProgramRecursionList.getList();
    }

    @GetMapping("/type/list")
    @Operation(summary = "프로그램 유형 리스트", description = "프로그램 유형을 조회합니다.")
    public List<ProgramDto> getTypeProgram(){
        return fetchProgramTypeList.getTypeList();
    }

    @GetMapping("/find/program/{programId}")
    @Operation(summary = "프로그램 찾기", description = "프로그램을 찾습니다.")
    public List<ProgramDto> findProgram(@PathVariable Integer programId){
        return findProgramService.getProgram(programId);
    }

    @GetMapping("/find/tree/{programId}")
    @Operation(summary = "프로그램 계층 리스트", description = "프로그램 계층을 조회합니다.")
    public List<ProgramPathDto> findProgramTree(@PathVariable Integer programId){
        return fetchProgramPathList.getPathList(programId);
    }
}
