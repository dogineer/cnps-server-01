package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.admin.program.service.FetchProgramRecursionList;
import com.develop.web.domain.admin.program.service.FetchProgramTypeList;
import com.develop.web.domain.users.user.service.DetailMemberFetcher;
import com.develop.web.domain.users.user.service.MemberListFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("ProgramPageFetcher")
public class ProgramPageFetcher implements PageFetcher {
    private final DetailMemberFetcher detailMemberFetcher;
    private final MemberListFetcher memberListFetcher;
    private final FetchProgramRecursionList fetchProgramRecursionList;
    private final FetchProgramTypeList fetchProgramTypeList;

    @Override
    public void fetchPage(AccountDto accountDto, Model model) {
        String account = accountDto.getAccount();

        model.addAttribute("UserList", memberListFetcher.getMemberList());
        model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
        model.addAttribute("Program", fetchProgramRecursionList.findProgramList());
        model.addAttribute("ProgramType", fetchProgramTypeList.findProgramTypeList());
    }
}
