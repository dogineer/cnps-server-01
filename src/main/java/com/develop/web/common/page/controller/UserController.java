package com.develop.web.common.page.controller;

import com.develop.web.common.page.dto.AccountDto;
import com.develop.web.common.page.service.IngestPageFetcher;
import com.develop.web.common.page.service.PageFetcher;
import com.develop.web.domain.auth.service.AuthChecker;
import com.develop.web.domain.page.dto.CriteriaDto;
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
@RequestMapping(value = "/user/*")
public class UserController {

    private final AuthChecker authChecker;
    private final PageFetcher
        clipPageFetcher;
    private final IngestPageFetcher ingestPageFetcher;

    private void initPageService(HttpSession session, Model model, PageFetcher pageFetcher) {
        String account = session.getAttribute("account").toString();
        Integer teamId = (Integer) session.getAttribute("teamId");

        AccountDto accountDto = new AccountDto(account, teamId);

        pageFetcher.fetchPage(accountDto, model);
    }

    @GetMapping("ingest")
    public String ingestPage(
        @RequestParam("page") int page,
        @RequestParam("limit") int limit,
        CriteriaDto criteriaDto, HttpSession session, Model model) throws CustomException {

        authChecker.blockOutsiders(session);

        String account = session.getAttribute("account").toString();
        Integer teamId = (Integer) session.getAttribute("teamId");

        criteriaDto = new CriteriaDto(page, limit);

        AccountDto accountDto = new AccountDto(account, teamId);

        ingestPageFetcher.fetchPageing(criteriaDto, accountDto, model);
        return "pages/ingest";
    }


    @GetMapping("clip")
    public String editPage(HttpSession session, Model model) throws CustomException {
        authChecker.blockOutsiders(session);
        initPageService(session, model, clipPageFetcher);
        return "pages/clip";
    }
}
