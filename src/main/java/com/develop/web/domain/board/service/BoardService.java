package com.develop.web.domain.board.service;

import com.develop.web.domain.board.vo.BoardVo;

import java.util.List;

public interface BoardService {
    void boardPost(BoardVo boardVo);

    List<BoardVo> listAll() throws Exception;
}
