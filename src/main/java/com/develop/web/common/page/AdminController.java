package com.develop.web.common.page;

import com.develop.web.domain.personnel.account.service.DetailMemberFetcher;
import com.develop.web.domain.auth.service.AuthChecker;
import com.develop.web.domain.personnel.account.service.MemberListFetcher;
import com.develop.web.domain.personnel.dept.service.DetailDeptFetcher;
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

    /**
     * @description 관리자 접근 권한 직원 관리 페이지
     * @return "redirect:/ 최초 페이지로 이동"
     * */
    @GetMapping("/user")
    public String Administrator(HttpSession session, Model model) {

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
}
