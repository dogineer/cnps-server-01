package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.admin.dept.service.DeptListFetcherService;
import com.develop.web.domain.admin.dept.service.DeptTopFetcherService;
import com.develop.web.domain.users.user.service.UserDetailFetcherService;
import com.develop.web.domain.users.user.service.UserListFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("deptPageFetcher")
public class DeptPageFetcher implements PageFetcher {
    private final UserDetailFetcherService userDetailFetcherService;
    private final UserListFetcherService userListFetcherService;
    private final DeptListFetcherService deptListFetcherService;
    private final DeptTopFetcherService deptTopFetcherService;

    @Override
    public void fetchPage(AccountDto accountDto, Model model) {
        String account = accountDto.getAccount();

        model.addAttribute("UserList", userListFetcherService.findUsers());
        model.addAttribute("MemberInfo", userDetailFetcherService.findMember(account));
        model.addAttribute("Depts", deptListFetcherService.findDeptList());
        model.addAttribute("TopDepts", deptTopFetcherService.findTopDept());
    }
}
