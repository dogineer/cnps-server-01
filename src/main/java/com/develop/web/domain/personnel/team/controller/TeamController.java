package com.develop.web.domain.personnel.team.controller;

import com.develop.web.domain.personnel.team.service.TeamListFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/program")
public class TeamController {

    private final TeamListFetcher teamListFetcher;

    /*
    * 팀 리스트 가져오기
    * */
    @PostMapping("/add")
    public String add() {
        teamListFetcher.getTeam();
        return "redirect:/Administrator";
    }
}
