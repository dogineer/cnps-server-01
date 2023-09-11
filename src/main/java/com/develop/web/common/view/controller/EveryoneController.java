package com.develop.web.common.view.controller;

import com.develop.web.domain.admin.dept.service.FindDeptList;
import com.develop.web.domain.admin.rank.service.RankListFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class EveryoneController {
    private final RankListFetcher rankListFetcher;
    private final FindDeptList findDeptList;

    /**
     * @description 권한 없는 모든 유저가 접근 가능한 페이지
     */
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        String version = "ALPHA VERSION";

        model.addAttribute("Ranks", rankListFetcher.getList());
        model.addAttribute("Depts", findDeptList.getDeptList());
        model.addAttribute("version", version);

        boolean isLogin = session.getAttribute("account") != null;

        if (isLogin) {
            boolean isAdmin = session.getAttribute("rank") != null && (int) session.getAttribute("rank") == 12;

            if (isAdmin) {
                return "redirect:/admin/management/user";
            } else {
                return "redirect:/user/clip";
            }
        } else {
            return "index";
        }
    }
}
