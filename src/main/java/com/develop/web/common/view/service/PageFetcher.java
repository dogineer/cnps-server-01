package com.develop.web.common.view.service;

import com.develop.web.common.view.dto.AccountDto;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface PageFetcher {
    void fetchPage(AccountDto accountDto, Model model);
}
