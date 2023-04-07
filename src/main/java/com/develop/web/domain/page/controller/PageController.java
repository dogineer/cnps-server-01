package com.develop.web.domain.page.controller;

import com.develop.web.domain.auth.service.AuthServiceSessionImpl;
import com.develop.web.domain.auth.vo.Role;
import com.develop.web.domain.board.service.BoardServiceImpl;
import com.develop.web.domain.page.service.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {
    private final AuthServiceSessionImpl authService;
    private final BoardServiceImpl boardService;
    private final PageService pageService;

    public PageController(AuthServiceSessionImpl authService, BoardServiceImpl boardService, PageService pageService) {
        this.authService = authService;
        this.boardService = boardService;
        this.pageService = pageService;
    }

    /*
    * 최초 페이지
    * */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /*
    * 홈화면 페이지
    * */
    @GetMapping("/home")
    public String home(HttpSession session, Model model) throws Exception {
        model.addAttribute("boardList", boardService.listAll());

        return pageService.redirectPage("/pages/home", session);
    }

    /*
     * 회원가입 페이지
     * */
    @GetMapping("/auth/signup")
    public String signUpForm() {
        return "/resources/templates/modal/modals/signup.html";
    }

    /*
     * 관리자 페이지
     * */
    @GetMapping("/Administrator")
    public String Administrator(HttpSession session, Model model) throws Exception {
        if (session.getAttribute("role") == Role.Administrator){
            model.addAttribute("UserList", authService.memberlistAll());
            model.addAttribute("boardList", boardService.listAll());
            System.out.println(model.getAttribute("UserList"));

            return pageService.redirectPage("pages/Administrator", session);
        }
        return "redirect:/";
    }
}
