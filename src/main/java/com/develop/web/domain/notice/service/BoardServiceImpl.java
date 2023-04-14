package com.develop.web.domain.notice.service;

import com.develop.web.domain.notice.mapper.BoardMapper;
import com.develop.web.domain.notice.vo.BoardVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    /*
    * 글 작성
    * */
    @Override
    public void boardPost(BoardVo boardVo) {
        boardMapper.insertBoard(boardVo);
    }

    /*
    * 글 목록 보기
    * */
    @Override
    public List<BoardVo> listAll(){
        return boardMapper.selectAllList();
    }
}
