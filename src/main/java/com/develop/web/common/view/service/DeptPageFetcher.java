package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.personnel.dept.service.DetailDeptFetcher;
import com.develop.web.domain.personnel.dept.service.FindDeptList;
import com.develop.web.domain.personnel.member.service.DetailMemberFetcher;
import com.develop.web.domain.personnel.member.service.MemberListFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("deptPageFetcher")
public class DeptPageFetcher implements PageFetcher {
    private final DetailDeptFetcher detailDeptFetcher;
    private final DetailMemberFetcher detailMemberFetcher;
    private final MemberListFetcher memberListFetcher;
    private final FindDeptList findDeptList;

    @Override
    public void fetchPage(AccountDto accountDto, Model model) {
        String account = accountDto.getAccount();

        model.addAttribute("UserList", memberListFetcher.getMemberList());
        model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
        model.addAttribute("Depts", findDeptList.getDeptList());
        model.addAttribute("DetailDept", detailDeptFetcher.getDetailDept(account));
    }
}
