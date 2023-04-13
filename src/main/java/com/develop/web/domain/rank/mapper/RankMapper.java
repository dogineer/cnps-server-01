package com.develop.web.domain.rank.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RankMapper {
    /** @description 직급 데이터 불러오기 */
    List<Map<Integer, String>> queryListRank();

    /** @description 직책 데이터 불러오기 */
    List<Map<Integer, String>> queryListRankEmployee();
}
