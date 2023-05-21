package com.develop.web.common.page;

import com.develop.web.domain.personnel.member.service.DetailMemberFetcher;
import com.develop.web.domain.auth.service.AuthChecker;
import com.develop.web.domain.personnel.member.service.MemberListFetcher;
import com.develop.web.domain.personnel.dept.service.DetailDeptFetcher;
import com.develop.web.domain.personnel.dept.service.FindDeptList;
import com.develop.web.domain.personnel.team.service.TeamListFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin/management")
public class AdminController {
    private final AuthChecker authChecker;
    private final MemberListFetcher memberListFetcher;
    private final DetailMemberFetcher detailMemberFetcher;
    private final DetailDeptFetcher detailDeptFetcher;
    private final FindDeptList findDeptList;
    private final TeamListFetcher teamListFetcher;

    @GetMapping("/user")
    public String userPage(HttpSession session, Model model) {

        try {
            authChecker.blockOutsiders(session);
            String account = session.getAttribute("account").toString();

            model.addAttribute("UserList", memberListFetcher.getMemberList());
            model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
            model.addAttribute("DetailDept", detailDeptFetcher.getDetailDept(account));

            return "pages/admin/management/user";
        } catch (NullPointerException e) {
            log.error("외부인 접근 불가");
        }

        return "redirect:/";
    }

    @GetMapping("/dept")
    public String deptPage(HttpSession session, Model model) {

        try {
            authChecker.blockOutsiders(session);
            String account = session.getAttribute("account").toString();

            model.addAttribute("UserList", memberListFetcher.getMemberList());
            model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
            model.addAttribute("Depts", findDeptList.getDeptList());
            model.addAttribute("DetailDept", detailDeptFetcher.getDetailDept(account));

            return "pages/admin/management/dept";
        } catch (NullPointerException e) {
            log.error("외부인 접근 불가");
        }

        return "redirect:/";
    }

    @GetMapping("/team")
    public String teamPage(HttpSession session, Model model) {

        try {
            authChecker.blockOutsiders(session);
            String account = session.getAttribute("account").toString();

            model.addAttribute("UserList", memberListFetcher.getMemberList());
            model.addAttribute("MemberInfo", detailMemberFetcher.getMember(account));
            model.addAttribute("TeamList", teamListFetcher.getTeam());
            model.addAttribute("Depts", findDeptList.getDeptList());
            model.addAttribute("DetailDept", detailDeptFetcher.getDetailDept(account));

            return "pages/admin/management/team";
        } catch (NullPointerException e) {
            log.error("외부인 접근 불가");
        }

        return "redirect:/";
    }
}
