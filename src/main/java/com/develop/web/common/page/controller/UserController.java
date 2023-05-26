package com.develop.web.common.page.controller;

import com.develop.web.common.page.dto.AccountDto;
import com.develop.web.common.page.service.PageFetcher;
import com.develop.web.domain.auth.service.AuthChecker;
import com.develop.web.global.exception.exception.AuthApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final AuthChecker authChecker;
    private final PageFetcher
        clipPageFetcher,
        ingestPageFetcher;

    private void initPageService(HttpSession session, Model model, PageFetcher pageFetcher) {
        AccountDto accountDto = new AccountDto();
        String account = session.getAttribute("account").toString();
        Integer teamId = (Integer) session.getAttribute("teamId");

        accountDto.setAccount(account);
        accountDto.setTeamId(teamId);

        pageFetcher.fetchPage(accountDto, model);
    }

    @GetMapping("/ingest")
    public String ingestPage(HttpSession session, Model model) throws AuthApiException {
        authChecker.blockOutsiders(session);
        initPageService(session, model, ingestPageFetcher);
        return "pages/ingest";
    }

    @GetMapping("/clip")
    public String editPage(HttpSession session, Model model) throws AuthApiException {
        authChecker.blockOutsiders(session);
        initPageService(session, model, clipPageFetcher);
        return "pages/clip";
    }
}
