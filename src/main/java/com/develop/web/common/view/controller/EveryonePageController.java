package com.develop.web.common.view.controller;

import com.develop.web.domain.admin.dept.service.DeptService;
import com.develop.web.domain.admin.position.service.PosListFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class EveryonePageController {
    private final PosListFetcherService posListFetcherService;
    private final DeptService deptService;

    /**
     * @description 권한 없는 모든 유저가 접근 가능한 페이지
     */
    @GetMapping("/s1/login")
    public String index(Model model, HttpSession session) {
        String version = "BETA 20231004 VERSION";

        model.addAttribute("posList", posListFetcherService.findPosList());
        model.addAttribute("TopDepts", deptService.findTopDept());
        model.addAttribute("Depts", deptService.findDeptList());
        model.addAttribute("version", version);

        boolean isLogin = session.getAttribute("account") != null;

        if (isLogin) {
            return "redirect:/s1/page/clip";
        } else {
            return "user/login";
        }
    }
}
