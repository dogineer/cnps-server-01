package com.develop.web.domain.notice.service;

import com.develop.web.domain.notice.vo.BoardVo;

import java.util.List;

public interface BoardService {
    void boardPost(BoardVo boardVo);

    List<BoardVo> listAll() throws Exception;
}
