package com.develop.web.domain.admin.position.mapper;

import com.develop.web.domain.admin.position.dto.PosDto;
import com.develop.web.domain.admin.position.dto.PosDetailDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PosMapper {
    void insertPos(PosDto posDto);

    void deletePos(Integer posId);
    List<PosDetailDto> selectListPos();
}
