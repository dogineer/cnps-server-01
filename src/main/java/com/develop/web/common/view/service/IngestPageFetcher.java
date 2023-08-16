package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.service.folder.service.TeamFolderGroupFetcher;
import com.develop.web.domain.service.ingest.service.IngestListFetcher;
import com.develop.web.domain.service.page.dto.CriteriaDto;
import com.develop.web.domain.service.page.dto.PageDto;
import com.develop.web.domain.service.upload.mapper.UploadMapper;
import com.develop.web.domain.admin.notice.service.PostListFetcher;
import com.develop.web.domain.admin.dept.service.DetailDeptFetcher;
import com.develop.web.domain.users.user.service.DetailMemberFetcher;
import com.develop.web.domain.users.team.service.TeamListFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Component("ingestPageFetcher")
public class IngestPageFetcher implements PageingService{
    private final PostListFetcher postListFetcher;
    private final DetailDeptFetcher detailDeptFetcher;
    private final DetailMemberFetcher detailMemberFetcher;
    private final TeamListFetcher teamListFetcher;
    private final TeamFolderGroupFetcher teamFolderGroupFetcher;
    private final IngestListFetcher ingestListFetcher;

    private final UploadMapper uploadMapper;

    @Override
    public void fetchPageing(CriteriaDto criteriaDto, AccountDto accountDto, Model model) {

        String account = accountDto.getAccount();
        Integer teamId = accountDto.getTeamId();

        int countTotal = uploadMapper.selectIngestCount();
        PageDto pageDto = new PageDto(countTotal, 10, criteriaDto);

        model.addAttribute("NoticeList", postListFetcher.getPost());
        model.addAttribute("TeamList", teamListFetcher.getTeam());
        model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
        model.addAttribute("TeamFolderList", teamFolderGroupFetcher.getTeamFolder(teamId));
        model.addAttribute("DetailDept", detailDeptFetcher.getDetailDept(account));
        model.addAttribute("IngestRequestList", ingestListFetcher.getIngestRequestList(criteriaDto));

        model.addAttribute("pageMaker", pageDto);

    }
}
