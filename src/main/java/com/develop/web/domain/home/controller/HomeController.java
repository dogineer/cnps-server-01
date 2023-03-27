package com.develop.web.domain.home.controller;

import com.develop.web.domain.auth.service.AuthServiceSessionImpl;
import com.develop.web.domain.board.service.BoardServiceImpl;
import com.develop.web.domain.board.vo.BoardVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class HomeController {
    private final AuthServiceSessionImpl authService;
    private final BoardServiceImpl boardService;

    public HomeController(AuthServiceSessionImpl authService, BoardServiceImpl boardService) {
        this.authService = authService;
        this.boardService = boardService;
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

        return redirectPage("home/home", session);
    }

    /*
     * 회원가입 페이지
     * */
    @GetMapping("/auth/signup")
    public String signUpForm() {
        return "auth/signup";
    }

    public String redirectPage(String url, HttpSession session){
        if (session.getAttribute("userid") == null){
            return "redirect:/";
        }

        return url;
    }
}
