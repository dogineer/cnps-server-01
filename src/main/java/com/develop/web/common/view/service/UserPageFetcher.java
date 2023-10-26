package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.admin.user.service.UserManagementService;
import com.develop.web.domain.member.user.mapper.UserMapper;
import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.common.view.dto.PageDto;
import com.develop.web.domain.member.user.service.UserListFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("userPageFetcher")
public class UserPageFetcher implements PageingService {
    private final UserManagementService userManagementService;
    private final UserListFetcherService userListFetcherService;
    private final UserMapper userMapper;

    @Override
    public void fetchPageing(CriteriaDto criteriaDto, AccountDto accountDto, Model model) {
        String account = accountDto.getAccount();

        int countTotal = userMapper.selectUserCount();
        PageDto pageDto = new PageDto(countTotal, 10, criteriaDto);

        model.addAttribute("UserList", userListFetcherService.findSortUsers(criteriaDto));
        model.addAttribute("UserDeleteList", userListFetcherService.findDeleteUsers(criteriaDto));
        model.addAttribute("MemberInfo", userManagementService.findMember(account));
        model.addAttribute("pageMaker", pageDto);
    }
}
