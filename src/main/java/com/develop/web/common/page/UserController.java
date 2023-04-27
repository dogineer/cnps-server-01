package com.develop.web.common.page;

import com.develop.web.domain.personnel.account.service.DetailMemberFetcher;
import com.develop.web.domain.auth.service.AuthChecker;
import com.develop.web.domain.personnel.dept.service.DetailDeptFetcher;
import com.develop.web.domain.folder.service.RootFolderListFetcher;
import com.develop.web.domain.media.ingest.service.IngestListFetcher;
import com.develop.web.domain.notice.service.PostListFetcher;
import com.develop.web.domain.personnel.team.service.TeamListFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final PostListFetcher postListFetcher;
    private final AuthChecker authChecker;
    private final DetailDeptFetcher detailDeptFetcher;
    private final DetailMemberFetcher detailMemberFetcher;
    private final TeamListFetcher teamListFetcher;
    private final IngestListFetcher ingestListFetcher;
    private final RootFolderListFetcher rootFolderListFetcher;

    /**
     * @description 직원만 접근 가능한 홈화면 페이지
     * @return "redirect:/ 최초 페이지로 이동"
     * */
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {

        try {
            authChecker.blockOutsiders(session);
            String account = session.getAttribute("account").toString();

            model.addAttribute("NoticeList", postListFetcher.getPost());
            model.addAttribute("TeamList", teamListFetcher.getTeam());
            model.addAttribute("IngestRequestList", ingestListFetcher.getIngestRequestList());
            model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
            model.addAttribute("DetailDept", detailDeptFetcher.getDetailDept(account));
            model.addAttribute("folderRootList", rootFolderListFetcher.getFolder());

            return "pages/home";
        } catch (NullPointerException e) {
            log.error("외부인 접근 불가");
        }

        return "redirect:/";
    }
}
