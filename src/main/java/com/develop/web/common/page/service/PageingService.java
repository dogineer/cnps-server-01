package com.develop.web.common.page.service;

import com.develop.web.common.page.dto.AccountDto;
import com.develop.web.domain.page.dto.CriteriaDto;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface PageingService {
    void fetchPageing(CriteriaDto criteriaDto, AccountDto accountDto, Model model);
}
