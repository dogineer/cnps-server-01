package com.develop.web.common.view.controller;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.common.view.service.*;
import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.domain.users.auth.service.InitAccountService;
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
    private final InitAccountService initAccountService;
    private final UserPageFetcher userPageFetcher;
    private final DeptPageFetcher deptPageFetcher;
    private final ProgramPageFetcher programPageFetcher;
    private final RankPageFetcher rankPageFetcher;

    @GetMapping("user")
    public String userPage(
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "limit", defaultValue = "50") int limit,
        HttpSession session, Model model) throws CustomException {

        AccountDto accountDto = initAccountService.session(session);
        CriteriaDto criteriaDto = new CriteriaDto(page, limit);

        userPageFetcher.fetchPageing(criteriaDto, accountDto, model);
        return "admin/user/admin_user_page";
    }

    @GetMapping("dept")
    public String deptPage(HttpSession session, Model model) throws CustomException {
        AccountDto accountDto = initAccountService.session(session);
        deptPageFetcher.fetchPage(accountDto, model);

        return "admin/dept/admin_dept_page";

    }

    @GetMapping("program")
    public String programPage(HttpSession session, Model model) throws CustomException {
        AccountDto accountDto = initAccountService.session(session);
        programPageFetcher.fetchPage(accountDto, model);

        return "admin/program/admin_program_page";
    }

    @GetMapping("rank")
    public String rankPage(HttpSession session, Model model) throws CustomException {
        AccountDto accountDto = initAccountService.session(session);
        rankPageFetcher.fetchPage(accountDto, model);

        return "admin/rank/admin_rank_page";
    }
}
