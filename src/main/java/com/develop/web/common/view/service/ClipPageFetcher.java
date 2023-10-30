package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.admin.user.service.UserManagementService;
import com.develop.web.domain.service.folder.service.ProgramFolderService;
import com.develop.web.domain.service.clip.mapper.ClipMapper;
import com.develop.web.domain.service.clip.service.ClipDataFetcherService;
import com.develop.web.domain.admin.notice.service.PostListFetcher;
import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.common.view.dto.PageDto;
import com.develop.web.domain.member.program.service.ProgramListFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("clipPageFetcher")
public class ClipPageFetcher implements PageingService {
    private final PostListFetcher postListFetcher;
    private final UserManagementService userManagementService;
    private final ProgramListFetcher programListFetcher;
    private final ProgramFolderService programFolderService;
    private final ClipDataFetcherService clipDataFetcherService;

    private final ClipMapper clipMapper;

    @Override
    public void fetchPageing(CriteriaDto criteriaDto, AccountDto accountDto, Model model) {

        String account = accountDto.getAccount();
        Integer programId = accountDto.getProgramId();
        Boolean isAdmin = accountDto.getIsAdmin();

        int countTotal = clipMapper.selectClipCount();
        PageDto pageDto = new PageDto(countTotal, 10, criteriaDto);

        model.addAttribute("NoticeList", postListFetcher.getPost());
        model.addAttribute("ProgramList", programListFetcher.findProgram());
        model.addAttribute("MemberInfo", userManagementService.findMember(account));
        model.addAttribute("ProgramRootFolderList", programFolderService.findProgramFolderRoot(programId, isAdmin));
        model.addAttribute("ClipList", clipDataFetcherService.findClipList(criteriaDto));

        model.addAttribute("pageMaker", pageDto);
    }
}
