package com.develop.web.domain.board.mapper;

import com.develop.web.domain.board.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    void insertBoard(BoardVo vo);
}
