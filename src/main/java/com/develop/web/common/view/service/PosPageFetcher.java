package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.admin.position.service.PositionService;
import com.develop.web.domain.admin.user.service.UserManagementService;
import com.develop.web.domain.member.user.service.UserListFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("PosPageFetcher")
public class PosPageFetcher implements PageFetcher {
    private final UserManagementService userManagementService;
    private final UserListFetcherService userListFetcherService;
    private final PositionService positionService;

    @Override
    public void fetchPage(AccountDto accountDto, Model model) {
        String account = accountDto.getAccount();

        model.addAttribute("UserList", userListFetcherService.findUsers());
        model.addAttribute("MemberInfo", userManagementService.findMember(account));
        model.addAttribute("posList", positionService.findPosList());
    }
}
