package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.users.auth.mapper.AuthMapper;
import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.common.view.dto.PageDto;
import com.develop.web.domain.users.user.service.UserDetailFetcherService;
import com.develop.web.domain.users.user.service.UserListFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("userPageFetcher")
public class UserPageFetcher implements PageingService {
    private final UserDetailFetcherService userDetailFetcherService;
    private final UserListFetcherService userListFetcherService;
    private final AuthMapper authMapper;

    @Override
    public void fetchPageing(CriteriaDto criteriaDto, AccountDto accountDto, Model model) {
        String account = accountDto.getAccount();

        int countTotal = authMapper.selectEmpCount();
        PageDto pageDto = new PageDto(countTotal, 10, criteriaDto);

        model.addAttribute("UserList", userListFetcherService.findSortUsers(criteriaDto));
        model.addAttribute("UserDeleteList", userListFetcherService.findDeleteUsers(criteriaDto));
        model.addAttribute("MemberInfo", userDetailFetcherService.findMember(account));
        model.addAttribute("pageMaker", pageDto);
    }
}
