package com.develop.web.common.view.controller;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.common.view.service.*;
import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.global.exception.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin/management/*")
public class AdminController {
    private final UserPageFetcher userPageFetcher;
    private final DeptPageFetcher deptPageFetcher;
    private final TeamPageFetcher teamPageFetcher;
    private final RankPageFetcher rankPageFetcher;

    private void initPageService(HttpSession session, Model model, PageFetcher pageFetcher) {

        String account = session.getAttribute("account").toString();
        Integer teamId = (Integer) session.getAttribute("teamId");
        Integer rankId = (Integer) session.getAttribute("rankId");

        AccountDto accountDto = new AccountDto(account, teamId, rankId);

        pageFetcher.fetchPage(accountDto, model);
    }

    @GetMapping("user")
    public String userPage(
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "limit", defaultValue = "50") int limit,
        CriteriaDto criteriaDto, HttpSession session, Model model) throws CustomException {

        String account = session.getAttribute("account").toString();
        Integer teamId = (Integer) session.getAttribute("teamId");
        Integer rankId = (Integer) session.getAttribute("rankId");

        criteriaDto = new CriteriaDto(page, limit);

        AccountDto accountDto = new AccountDto(account, teamId, rankId);

        userPageFetcher.fetchPageing(criteriaDto, accountDto, model);
        return "admin/user/admin_user_page";
    }

    @GetMapping("dept")
    public String deptPage(HttpSession session, Model model) throws CustomException {
        initPageService(session, model, deptPageFetcher);
        return "admin/dept/admin_dept_page";

    }

    @GetMapping("team")
    public String teamPage(HttpSession session, Model model) throws CustomException {
        initPageService(session, model, teamPageFetcher);
        return "admin/team/admin_team_page";
    }

    @GetMapping("rank")
    public String rankPage(HttpSession session, Model model) throws CustomException {
        initPageService(session, model, rankPageFetcher);
        return "admin/rank/admin_rank_page";
    }
}
