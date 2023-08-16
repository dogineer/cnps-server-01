package com.develop.web.domain.admin.team.controller;

import com.develop.web.domain.admin.team.service.AddTeamService;
import com.develop.web.domain.admin.team.service.DeleteTeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "팀 관리", description = "어드민 팀 관리 api")
@RequiredArgsConstructor
@RequestMapping(value = "/admin/team")
public class AdminTeamController {
    private final AddTeamService addTeamService;
    private final DeleteTeamService deleteTeamService;

    @PostMapping("/add")
    @Operation(summary = "팀 추가하기", description = "팀을 추가합니다.")
    public void addTeam(String teamName, Integer teamType) {
        addTeamService.insertTeam(teamName, teamType);
    }

    @PostMapping("/delete")
    @Operation(summary = "팀 삭제하기", description = "팀을 삭제합니다.")
    public void deleteTeam(Integer teamId) {
        deleteTeamService.deleteTeam(teamId);
    }
}
