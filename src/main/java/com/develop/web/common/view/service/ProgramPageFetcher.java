package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.admin.program.service.ProgramService;
import com.develop.web.domain.admin.user.service.UserManagementService;
import com.develop.web.domain.member.user.service.UserListFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("ProgramPageFetcher")
public class ProgramPageFetcher implements PageFetcher {
    private final UserManagementService userManagementService;
    private final UserListFetcherService userListFetcherService;
    private final ProgramService programService;

    @Override
    public void fetchPage(AccountDto accountDto, Model model) {
        String account = accountDto.getAccount();

        model.addAttribute("UserList", userListFetcherService.findUsers());
        model.addAttribute("MemberInfo", userManagementService.findMember(account));
        model.addAttribute("Program", programService.findProgramList());
        model.addAttribute("ProgramType", programService.findProgramTypeList());
    }
}
