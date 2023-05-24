package com.develop.web.common.page.controller;

import com.develop.web.common.page.dto.AccountDto;
import com.develop.web.common.page.service.PageFetcher;
import com.develop.web.domain.auth.service.AuthChecker;
import com.develop.web.global.exception.exception.RestApiException;
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

    /**
     * @return "redirect:/ 최초 페이지로 이동"
     * @description 직원만 접근 가능한 홈화면 페이지
     */
    @GetMapping("/ingest")
    public String ingestPage(HttpSession session, Model model) {
        authChecker.blockOutsiders(session);

        AccountDto accountDto = new AccountDto();
        String account = session.getAttribute("account").toString();
        Integer teamId = (Integer) session.getAttribute("teamId");

        accountDto.setAccount(account);
        accountDto.setTeamId(teamId);

        try {
            ingestPageFetcher.fetchPage(accountDto, model);

            return "pages/ingest";
        } catch (RestApiException e) {
            log.error("외부인 접근 불가");
            return "redirect:/";
        }
    }

    @GetMapping("/clip")
    public String editPage(HttpSession session, Model model) {
        authChecker.blockOutsiders(session);

        AccountDto accountDto = new AccountDto();
        String account = session.getAttribute("account").toString();
        Integer teamId = (Integer) session.getAttribute("teamId");

        accountDto.setAccount(account);
        accountDto.setTeamId(teamId);

        clipPageFetcher.fetchPage(accountDto, model);
        return "pages/clip";
    }
}
