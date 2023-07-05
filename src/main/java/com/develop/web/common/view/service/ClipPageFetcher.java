package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.folder.service.RootFolderListFetcher;
import com.develop.web.domain.media.clip.mapper.ClipMapper;
import com.develop.web.domain.media.clip.service.ClipDataListFetcher;
import com.develop.web.domain.media.upload.mapper.UploadMapper;
import com.develop.web.domain.notice.service.PostListFetcher;
import com.develop.web.domain.page.dto.CriteriaDto;
import com.develop.web.domain.page.dto.PageDto;
import com.develop.web.domain.personnel.dept.service.DetailDeptFetcher;
import com.develop.web.domain.personnel.member.service.DetailMemberFetcher;
import com.develop.web.domain.personnel.team.service.TeamListFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("clipPageFetcher")
public class ClipPageFetcher implements PageingService {
    private final PostListFetcher postListFetcher;
    private final DetailDeptFetcher detailDeptFetcher;
    private final DetailMemberFetcher detailMemberFetcher;
    private final TeamListFetcher teamListFetcher;
    private final RootFolderListFetcher rootFolderListFetcher;
    private final ClipDataListFetcher clipDataListFetcher;

    private final ClipMapper clipMapper;

    @Override
    public void fetchPageing(CriteriaDto criteriaDto, AccountDto accountDto, Model model) {

        String account = accountDto.getAccount();
        Integer teamId = accountDto.getTeamId();

        int countTotal = clipMapper.selectClipCount();
        PageDto pageDto = new PageDto(countTotal, 10, criteriaDto);

        model.addAttribute("NoticeList", postListFetcher.getPost());
        model.addAttribute("TeamList", teamListFetcher.getTeam());
        model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
        model.addAttribute("folderRootList", rootFolderListFetcher.getFolder());
        model.addAttribute("DetailDept", detailDeptFetcher.getDetailDept(account));
        model.addAttribute("clips", clipDataListFetcher.getClipList(criteriaDto));

        model.addAttribute("pageMaker", pageDto);
    }
}
