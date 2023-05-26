package com.develop.web.common.page.service;

import com.develop.web.common.page.dto.AccountDto;
import com.develop.web.domain.folder.service.RootFolderListFetcher;
import com.develop.web.domain.media.clip.service.ClipDataListFetcher;
import com.develop.web.domain.notice.service.PostListFetcher;
import com.develop.web.domain.personnel.dept.service.DetailDeptFetcher;
import com.develop.web.domain.personnel.member.service.DetailMemberFetcher;
import com.develop.web.domain.personnel.team.service.TeamListFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("clipPageFetcher")
public class ClipPageFetcher implements PageFetcher {
    private final PostListFetcher postListFetcher;
    private final DetailDeptFetcher detailDeptFetcher;
    private final DetailMemberFetcher detailMemberFetcher;
    private final TeamListFetcher teamListFetcher;
    private final RootFolderListFetcher rootFolderListFetcher;
    private final ClipDataListFetcher clipDataListFetcher;

    @Override
    public void fetchPage(AccountDto accountDto, Model model) {
        String account = accountDto.getAccount();

        model.addAttribute("NoticeList", postListFetcher.getPost());
        model.addAttribute("TeamList", teamListFetcher.getTeam());
        model.addAttribute("clips", clipDataListFetcher.getClipList());
        model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
        model.addAttribute("folderRootList", rootFolderListFetcher.getFolder());
        model.addAttribute("DetailDept", detailDeptFetcher.getDetailDept(account));
    }
}
