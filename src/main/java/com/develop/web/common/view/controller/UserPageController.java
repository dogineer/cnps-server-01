package com.develop.web.common.view.controller;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.common.view.service.ClipPageFetcher;
import com.develop.web.common.view.service.IngestPageFetcher;
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
@RequestMapping(value = "/service")
public class UserPageController {
    private final ClipPageFetcher clipPageFetcher;
    private final IngestPageFetcher ingestPageFetcher;
    private final InitAccountService initAccountService;

    @GetMapping("/ingest")
    public String ingestPage(
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "limit", defaultValue = "50") int limit,
        HttpSession session, Model model) throws CustomException {
        AccountDto accountDto = initAccountService.session(session);
        CriteriaDto criteriaDto = new CriteriaDto(page, limit);

        ingestPageFetcher.fetchPageing(criteriaDto, accountDto, model);
        return "user/ingest/ingest_page";
    }


    @GetMapping("/clip")
    public String editPage(
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "limit", defaultValue = "50") int limit,
        HttpSession session, Model model) throws CustomException {
        AccountDto accountDto = initAccountService.session(session);
        CriteriaDto criteriaDto = new CriteriaDto(page, limit);

        clipPageFetcher.fetchPageing(criteriaDto, accountDto, model);
        return "user/clip/clip_page";
    }
}
