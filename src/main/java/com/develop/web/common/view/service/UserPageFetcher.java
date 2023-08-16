package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.users.auth.mapper.AuthMapper;
import com.develop.web.domain.service.page.dto.CriteriaDto;
import com.develop.web.domain.service.page.dto.PageDto;
import com.develop.web.domain.admin.dept.service.DetailDeptFetcher;
import com.develop.web.domain.users.user.service.DetailMemberFetcher;
import com.develop.web.domain.users.user.service.MemberListFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("userPageFetcher")
public class UserPageFetcher implements PageingService {
    private final DetailDeptFetcher detailDeptFetcher;
    private final DetailMemberFetcher detailMemberFetcher;
    private final MemberListFetcher memberListFetcher;
    private final AuthMapper authMapper;

    @Override
    public void fetchPageing(CriteriaDto criteriaDto, AccountDto accountDto, Model model) {
        String account = accountDto.getAccount();

        int countTotal = authMapper.selectEmpCount();
        PageDto pageDto = new PageDto(countTotal, 10, criteriaDto);

        model.addAttribute("UserList", memberListFetcher.getMemberGetList(criteriaDto));
        model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
        model.addAttribute("DetailDept", detailDeptFetcher.getDetailDept(account));
        model.addAttribute("pageMaker", pageDto);
    }
}
