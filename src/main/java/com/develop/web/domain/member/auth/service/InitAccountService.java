package com.develop.web.domain.member.auth.service;

import com.develop.web.common.view.dto.AccountDto;
import com.develop.web.domain.admin.user.dto.Role;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Service
public class InitAccountService implements AuthService {

    @Override
    public AccountDto session(HttpSession session) {
        String account = session.getAttribute("account").toString();
        Integer programId = (Integer) session.getAttribute("programId");
        boolean isAdmin = Objects.equals(session.getAttribute("role"), Role.ADMIN.getAuthority());

        return new AccountDto(account, programId, isAdmin);
    }

    @Override
    public AccountDto token(String token) {
        return null;
    }
}
