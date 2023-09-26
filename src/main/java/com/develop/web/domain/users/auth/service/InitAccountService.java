package com.develop.web.domain.users.auth.service;

import com.develop.web.common.view.dto.AccountDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class InitAccountService implements AuthService {
    public AccountDto session(HttpSession session) {
        String account = session.getAttribute("account").toString();
        Integer programId = (Integer) session.getAttribute("programId");
        boolean isAdmin = session.getAttribute("rankId") != null && (int) session.getAttribute("rankId") == 12;

        return new AccountDto(account, programId, isAdmin);
    }

    @Override
    public AccountDto token(String token) {
        return null;
    }
}
