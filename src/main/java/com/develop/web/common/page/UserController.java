package com.develop.web.common.page;

import com.develop.web.domain.folder.service.TeamFolderGroupFetcher;
import com.develop.web.domain.media.clip.service.ClipDataListFetcher;
import com.develop.web.domain.personnel.member.service.DetailMemberFetcher;
import com.develop.web.domain.auth.service.AuthChecker;
import com.develop.web.domain.personnel.dept.service.DetailDeptFetcher;
import com.develop.web.domain.folder.service.RootFolderListFetcher;
import com.develop.web.domain.media.ingest.service.IngestListFetcher;
import com.develop.web.domain.notice.service.PostListFetcher;
import com.develop.web.domain.personnel.team.service.TeamListFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final PostListFetcher postListFetcher;
    private final AuthChecker authChecker;
    private final DetailDeptFetcher detailDeptFetcher;
    private final DetailMemberFetcher detailMemberFetcher;
    private final TeamListFetcher teamListFetcher;
    private final IngestListFetcher ingestListFetcher;
    private final RootFolderListFetcher rootFolderListFetcher;
    private final TeamFolderGroupFetcher teamFolderGroupFetcher;
    private final ClipDataListFetcher clipDataListFetcher;

    /**
     * @description 직원만 접근 가능한 홈화면 페이지
     * @return "redirect:/ 최초 페이지로 이동"
     * */
    @GetMapping("/ingest")
    public String ingestPage(HttpSession session, Model model) {

        try {
            authChecker.blockOutsiders(session);

            String account = session.getAttribute("account").toString();
            Integer teamId = (Integer) session.getAttribute("teamId");

            model.addAttribute("NoticeList", postListFetcher.getPost());
            model.addAttribute("TeamList", teamListFetcher.getTeam());
            model.addAttribute("IngestRequestList", ingestListFetcher.getIngestRequestList());
            model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
            model.addAttribute("TeamFolderList", teamFolderGroupFetcher.getTeamFolder(teamId));
            model.addAttribute("DetailDept", detailDeptFetcher.getDetailDept(account));

            return "pages/ingest";
        } catch (RuntimeException e) {
            log.error("외부인 접근 불가");
            return "redirect:/";
        }
    }

    @GetMapping("/clip")
    public String editPage(HttpSession session, Model model) {

        try {
            authChecker.blockOutsiders(session);

            String account = session.getAttribute("account").toString();

            model.addAttribute("NoticeList", postListFetcher.getPost());
            model.addAttribute("TeamList", teamListFetcher.getTeam());
            model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
            model.addAttribute("DetailDept", detailDeptFetcher.getDetailDept(account));
            model.addAttribute("folderRootList", rootFolderListFetcher.getFolder());
            model.addAttribute("clips", clipDataListFetcher.getClipList());

            return "pages/clip";
        } catch (NullPointerException | UsernameNotFoundException | AccessDeniedException e) {
            log.error(e.getMessage());
            session.invalidate();
            return "redirect:/";
        }
    }
}
