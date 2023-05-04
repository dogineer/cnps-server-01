package com.develop.web.domain.personnel.team.controller;

import com.develop.web.domain.personnel.team.service.TeamListFetcher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@Tag(name = "팀 관리", description = "Swagger 테스트용 API")
@RequiredArgsConstructor
@RequestMapping(value = "/program")
public class TeamController {

    private final TeamListFetcher teamListFetcher;

    @PostMapping("/add")
    @Operation(summary = "팀 리스트 가져오기", description = "팀 전체 정보를 가져옵니다.")
    public String list() {
        teamListFetcher.getTeam();
        return "redirect:/Administrator";
    }

    @GetMapping("/get")
    @Operation(summary = "팀 리스트 가져오기", description = "팀 전체 정보를 가져옵니다.")
    public void fetcherTeamList() {
        teamListFetcher.getTeam();
    }
}
