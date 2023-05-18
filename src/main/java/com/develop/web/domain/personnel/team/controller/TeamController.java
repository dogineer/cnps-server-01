package com.develop.web.domain.personnel.team.controller;

import com.develop.web.domain.personnel.team.dto.TeamDto;
import com.develop.web.domain.personnel.team.service.TeamListFetcher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "팀 관리", description = "Swagger 테스트용 API")
@RequiredArgsConstructor
@RequestMapping(value = "/team")
public class TeamController {

    private final TeamListFetcher teamListFetcher;

    @PostMapping("/add")
    @Operation(summary = "팀 추가하기", description = "팀을 추가합니다.")
    public void list() {
        teamListFetcher.getTeam();
    }

    @GetMapping("/list")
    @Operation(summary = "팀 리스트 가져오기", description = "팀 전체 정보를 가져옵니다.")
    public List<TeamDto> fetcherTeamList() {
        return teamListFetcher.getTeam();
    }
}
