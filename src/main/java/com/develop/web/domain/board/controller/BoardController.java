package com.develop.web.domain.board.controller;

import com.develop.web.domain.board.vo.BoardVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    /*
    * 글쓰기 컨트롤러
    * */
    @PostMapping(value = "/post")
    public void boardPost(BoardVo vo){

    }
}
