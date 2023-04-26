package com.develop.web.domain.personnel.rank.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RankMapper {
    /** @description 직급 데이터 불러오기 */
    List<Map<Integer, String>> selectListRank();

    /** @description 직책 데이터 불러오기 */
    List<Map<Integer, String>> queryListRankEmployee();
}
