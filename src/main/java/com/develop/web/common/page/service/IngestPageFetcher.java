package com.develop.web.common.page.service;

import com.develop.web.common.page.dto.AccountDto;
import com.develop.web.domain.folder.service.TeamFolderGroupFetcher;
import com.develop.web.domain.media.ingest.service.IngestListFetcher;
import com.develop.web.domain.notice.service.PostListFetcher;
import com.develop.web.domain.personnel.dept.service.DetailDeptFetcher;
import com.develop.web.domain.personnel.member.service.DetailMemberFetcher;
import com.develop.web.domain.personnel.team.service.TeamListFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component
public class IngestPageFetcher implements PageFetcher {
    private final PostListFetcher postListFetcher;
    private final DetailDeptFetcher detailDeptFetcher;
    private final DetailMemberFetcher detailMemberFetcher;
    private final TeamListFetcher teamListFetcher;
    private final TeamFolderGroupFetcher teamFolderGroupFetcher;
    private final IngestListFetcher ingestListFetcher;

    @Override
    public void fetchPage(AccountDto accountDto, Model model) {
        String account = accountDto.getAccount();
        Integer teamId = accountDto.getTeamId();

        model.addAttribute("NoticeList", postListFetcher.getPost());
        model.addAttribute("TeamList", teamListFetcher.getTeam());
        model.addAttribute("IngestRequestList", ingestListFetcher.getIngestRequestList());
        model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
        model.addAttribute("TeamFolderList", teamFolderGroupFetcher.getTeamFolder(teamId));
        model.addAttribute("DetailDept", detailDeptFetcher.getDetailDept(account));
    }
}
