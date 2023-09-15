package com.develop.web.domain.admin.rank.controller;

import com.develop.web.domain.admin.rank.dto.NewRankDto;
import com.develop.web.domain.admin.rank.dto.RankDto;
import com.develop.web.domain.admin.rank.service.NewRank;
import com.develop.web.domain.admin.rank.service.RankListFetcher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "어드민 > 직급 및 직책 관리", description = "직급 코드 0, 직책 코드 8")
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class RankController {
    private final NewRank newRank;
    private final RankListFetcher rankListFetcher;

    @PostMapping("/rank/new/")
    @Operation(summary = "직급 및 직책 생성", description = "직급 코드 0, 직책 코드 8")
    public void newDept(@RequestBody NewRankDto rankDto){
        newRank.setNewRank(rankDto);
    }

    @GetMapping("/rank/list")
    @Operation(summary = "직급 및 직책 목록", description = "직급 및 직책 목록을 조회합니다.")
    public List<RankDto> getRankList(){
        return rankListFetcher.getRankList();
    }
}
