package com.develop.web.domain.member.auth.service;

import com.develop.web.common.view.dto.AccountDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface AuthService {
    AccountDto session(HttpSession session);
    AccountDto token(String token);
}
