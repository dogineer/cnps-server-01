package com.develop.web.domain.board.service;

import com.develop.web.domain.board.mapper.BoardMapper;
import com.develop.web.domain.board.vo.BoardVo;
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
        System.out.println("\nBoardService - POST");
        System.out.println("유저 " + boardVo.getWriter());
        System.out.println("제목 " + boardVo.getTitle());
        System.out.println("내용 " + boardVo.getContent());

        boardMapper.insertBoard(boardVo);
    }

    /*
    * 글 목록 보기
    * */
    @Override
    public List<BoardVo> listAll() throws Exception{

        return boardMapper.selectAllList();
    }
}
