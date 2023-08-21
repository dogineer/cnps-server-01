package com.develop.web.common.view.controller;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.common.view.service.PageFetcher;
import com.develop.web.common.view.service.PageingService;
import com.develop.web.domain.service.page.dto.CriteriaDto;
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
    private final PageingService userPageFetcher;
    private final PageFetcher deptPageFetcher;
    private final PageFetcher teamPageFetcher;
    private final PageFetcher rankPageFetcher;

    private void initPageService(HttpSession session, Model model, PageFetcher pageFetcher) {

        String account = session.getAttribute("account").toString();
        Integer teamId = (Integer) session.getAttribute("teamId");

        AccountDto accountDto = new AccountDto(account, teamId);

        pageFetcher.fetchPage(accountDto, model);
    }

    @GetMapping("user")
    public String userPage(
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "limit", defaultValue = "50") int limit,
        CriteriaDto criteriaDto, HttpSession session, Model model) throws CustomException {

        String account = session.getAttribute("account").toString();
        Integer teamId = (Integer) session.getAttribute("teamId");

        criteriaDto = new CriteriaDto(page, limit);

        AccountDto accountDto = new AccountDto(account, teamId);

        userPageFetcher.fetchPageing(criteriaDto, accountDto, model);
        return "pages/admin/management/AdminUserPage";
    }

    @GetMapping("dept")
    public String deptPage(HttpSession session, Model model) throws CustomException {
        initPageService(session, model, deptPageFetcher);
        return "pages/admin/management/AdminDeptPage";

    }

    @GetMapping("team")
    public String teamPage(HttpSession session, Model model) throws CustomException {
        initPageService(session, model, teamPageFetcher);
        return "pages/admin/management/AdminTeamPage";
    }

    @GetMapping("rank")
    public String rankPage(HttpSession session, Model model) throws CustomException {
        initPageService(session, model, rankPageFetcher);
        return "pages/admin/management/AdminRankPage";
    }
}
