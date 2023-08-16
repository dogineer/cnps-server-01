package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.admin.dept.service.DetailDeptFetcher;
import com.develop.web.domain.users.user.service.DetailMemberFetcher;
import com.develop.web.domain.users.user.service.MemberListFetcher;
import com.develop.web.domain.admin.rank.service.RankListFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("rankPageFetcher")
public class RankPageFetcher implements PageFetcher {
    private final DetailDeptFetcher detailDeptFetcher;
    private final DetailMemberFetcher detailMemberFetcher;
    private final MemberListFetcher memberListFetcher;
    private final RankListFetcher rankListFetcher;

    @Override
    public void fetchPage(AccountDto accountDto, Model model) {
        String account = accountDto.getAccount();

        model.addAttribute("UserList", memberListFetcher.getMemberList());
        model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
        model.addAttribute("RankList", rankListFetcher.getList());
    }
}
