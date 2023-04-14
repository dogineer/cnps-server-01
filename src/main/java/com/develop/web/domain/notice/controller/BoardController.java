package com.develop.web.domain.notice.controller;

import com.develop.web.domain.notice.service.BoardService;
import com.develop.web.domain.notice.vo.BoardVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /*
    * 글쓰기 컨트롤러
    * */
    @PostMapping(value = "/post")
    public String boardPost(BoardVo form, HttpSession session){
        BoardVo boardVo = new BoardVo(
                form.getWriter(),
                form.getTitle(),
                form.getContent()
        );

        boardService.boardPost(boardVo);
        return "redirect:/Administrator";
    }


}
