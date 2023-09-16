package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.admin.dept.service.FindDeptList;
import com.develop.web.domain.users.user.service.DetailMemberFetcher;
import com.develop.web.domain.users.user.service.MemberListFetcher;
import com.develop.web.domain.users.team.service.TeamListFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("teamPageFetcher")
public class TeamPageFetcher implements PageFetcher {
    private final DetailMemberFetcher detailMemberFetcher;
    private final MemberListFetcher memberListFetcher;
    private final FindDeptList findDeptList;
    private final TeamListFetcher teamListFetcher;

    @Override
    public void fetchPage(AccountDto accountDto, Model model) {
        String account = accountDto.getAccount();

        model.addAttribute("UserList", memberListFetcher.getMemberList());
        model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
        model.addAttribute("TeamList", teamListFetcher.getTeam());
        model.addAttribute("TeamTypeList", teamListFetcher.getTeamType());
        model.addAttribute("Depts", findDeptList.getDeptList());
    }
}
