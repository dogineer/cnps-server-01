package com.develop.web.domain.users.program.controller;

import com.develop.web.domain.users.program.dto.ProgramUserDto;
import com.develop.web.domain.users.program.service.ProgramListFetcher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "팀", description = "유저 팀 API")
@RequiredArgsConstructor
@RequestMapping(value = "/program")
public class ProgramController {

    private final ProgramListFetcher programListFetcher;

    @GetMapping("/list")
    @Operation(summary = "팀 리스트 가져오기", description = "팀 전체 정보를 가져옵니다.")
    public List<ProgramUserDto> fetcherProgramList() {
        return programListFetcher.getProgram();
    }

    @GetMapping("/list/Type")
    @Operation(summary = "프로그램 타입 가져오기", description = "프로그램 타입을 가져옵니다.")
    public List<ProgramUserDto> fetcherProgramTypeList() {
        return programListFetcher.getProgramType();
    }
}
