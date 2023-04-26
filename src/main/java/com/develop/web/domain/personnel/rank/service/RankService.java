package com.develop.web.domain.personnel.rank.service;

import java.util.List;
import java.util.Map;

public interface RankService {
    /**
     * @description 직급 데이터 불러오기 서비스
     * Integer 상위 직급 ID, String 직급 이름
     */
    List<Map<Integer, String>> getList();
}
