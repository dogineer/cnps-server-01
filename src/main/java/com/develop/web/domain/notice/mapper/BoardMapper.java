package com.develop.web.domain.notice.mapper;

import com.develop.web.domain.notice.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    void insertBoard(BoardVo vo);

    List<BoardVo> selectAllList();
}
