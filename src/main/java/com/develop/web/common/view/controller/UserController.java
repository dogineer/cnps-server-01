package com.develop.web.common.view.controller;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.common.view.service.ClipPageFetcher;
import com.develop.web.common.view.service.IngestPageFetcher;
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
@RequestMapping(value = "/user/*")
public class UserController {
    private final ClipPageFetcher clipPageFetcher;
    private final IngestPageFetcher ingestPageFetcher;

    private AccountDto initPageService(HttpSession session) {

        String account = session.getAttribute("account").toString();
        Integer teamId = (Integer) session.getAttribute("teamId");

        return new AccountDto(account, teamId);
    }

    @GetMapping("ingest")
    public String ingestPage(
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "limit", defaultValue = "50") int limit,
        HttpSession session, Model model) throws CustomException {
        AccountDto accountDto = initPageService(session);
        ingestPageFetcher.fetchPageing(new CriteriaDto(page, limit), accountDto, model);
        return "pages/ingest";
    }


    @GetMapping("clip")
    public String editPage(
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "limit", defaultValue = "50") int limit,
        HttpSession session, Model model) throws CustomException {
        AccountDto accountDto = initPageService(session);
        clipPageFetcher.fetchPageing(new CriteriaDto(page, limit), accountDto, model);
        return "pages/clip";
    }
}
