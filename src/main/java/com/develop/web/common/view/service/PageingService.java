package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.service.page.dto.CriteriaDto;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface PageingService {
    void fetchPageing(CriteriaDto criteriaDto, AccountDto accountDto, Model model);
}
