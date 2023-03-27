package com.develop.web.domain.board.mapper;

import com.develop.web.domain.board.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    void insertBoard(BoardVo vo);

    List<BoardVo> selectAllList();
}
