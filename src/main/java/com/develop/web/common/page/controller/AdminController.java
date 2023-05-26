package com.develop.web.common.page.controller;

import com.develop.web.common.page.dto.AccountDto;
import com.develop.web.common.page.service.PageFetcher;
import com.develop.web.domain.auth.service.AdminChecker;
import com.develop.web.domain.auth.service.AuthChecker;
import com.develop.web.global.exception.exception.AuthApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin/management")
public class AdminController {
    private final AuthChecker authChecker;
    private final AdminChecker adminChecker;;

    private final PageFetcher userPageFetcher;
    private final PageFetcher deptPageFetcher;
    private final PageFetcher teamPageFetcher;

    private void initPageService(HttpSession session, Model model, PageFetcher pageFetcher) {
        AccountDto accountDto = new AccountDto();
        String account = session.getAttribute("account").toString();
        Integer teamId = (Integer) session.getAttribute("teamId");

        accountDto.setAccount(account);
        accountDto.setTeamId(teamId);

        pageFetcher.fetchPage(accountDto, model);
    }

    @GetMapping("/user")
    public String userPage(HttpSession session, Model model) throws AuthApiException {
        authChecker.blockOutsiders(session);
        adminChecker.rankPermissionCheck(session);
        initPageService(session, model, userPageFetcher);

        return "pages/admin/management/user";
    }

    @GetMapping("/dept")
    public String deptPage(HttpSession session, Model model) throws AuthApiException {

        authChecker.blockOutsiders(session);
        adminChecker.rankPermissionCheck(session);
        initPageService(session, model, deptPageFetcher);

        return "pages/admin/management/dept";

    }

    @GetMapping("/team")
    public String teamPage(HttpSession session, Model model) throws AuthApiException {

        authChecker.blockOutsiders(session);
        adminChecker.rankPermissionCheck(session);
        initPageService(session, model, teamPageFetcher);

        return "pages/admin/management/team";
    }
}
