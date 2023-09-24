package com.develop.web.domain.users.auth.service;

import com.develop.web.common.view.dto.AccountDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class InitAccountService implements AuthService {
    public AccountDto session(HttpSession session) {
        String account = session.getAttribute("account").toString();
        Integer teamId = (Integer) session.getAttribute("teamId");
        Integer rank = (Integer) session.getAttribute("rankId");
        boolean isAdmin = session.getAttribute("rank") != null && (int) session.getAttribute("rank") == 12;

        return new AccountDto(account, teamId, rank, isAdmin);
    }

    @Override
    public AccountDto token(String token) {
        return null;
    }
}
