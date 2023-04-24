package com.develop.web.domain.account.controller;

import com.develop.web.domain.account.dto.PasswordChangeRequest;
import com.develop.web.domain.account.dto.TeamUpdateParam;
import com.develop.web.domain.account.service.CreateAccount;
import com.develop.web.domain.account.dto.Member;
import com.develop.web.domain.account.service.DeleteMember;
import com.develop.web.domain.account.service.ModifyPassword;
import com.develop.web.domain.account.service.ModifyTeam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@Tag(name = "사용자 관리", description = "Swagger 테스트용 API")
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AccountController {
    private final CreateAccount createAccount;
    private final ModifyPassword modifyPassword;
    private final DeleteMember deleteMember;
    private final ModifyTeam modifyTeam;

    /**
     * @description 회원가입 서비스
     * @return "redirect:/ 최초 페이지로 이동"
     * */
    @PostMapping(value = "/signup")
    @Operation(summary = "회원가입", description = "회원가입에 양식의 데이터를 서버에 저장합니다.")
    public String createAccount(Member member) {
        try {
            createAccount.addMember(member);
        } catch (DuplicateMemberException e){
            log.error("이미 회원가입된 아이디입니다. 다른 아이디를 입력해주세요.");
            return "redirect:/";
        }
        return "redirect:/";
    }

    /*
     * @description 비밀번호 변경 서비스
     * @return "redirect:/ 최초 페이지로 이동"
     * */
    @PutMapping("/changePassword")
    @Operation(summary = "비밀번호 변경", description = "새로운 비밀번호로 변경합니다.")
    public String changePassword(PasswordChangeRequest passwordChangeRequest, HttpSession session) {
        String account = (String) session.getAttribute("account");

        try {
            modifyPassword.setPassword(account, passwordChangeRequest);

            session.invalidate();
            log.info("비밀번호 변경이 완료됐습니다. 다시 로그인 해주세요.");
        } catch (BadCredentialsException e) {
            log.error("비밀번호가 맞지 않음");
        }

        return "redirect:/";
    }

    /**
     * @description 직원 삭제 서비스
     * @return "redirect:/ 관리자 페이지"
     * */
    @PutMapping("/member/delete/{account}")
    @Operation(summary = "사용자 삭제 플래그", description = "사용자를 완전히 삭제하지 않고, delFlag를 사용합니다.")
    public String userDelete(@PathVariable String account){
        deleteMember.updateMemberDeleteFlag(account);

        return "redirect:/management/employee";
    }

    /**
     * @description 직원 팀 업데이트
     * */
    @PutMapping("/member/team/update")
    @Operation(summary = "사용자 팀 변경", description = "사용자의 팀을 변경합니다.")
    public String updateTeam(TeamUpdateParam param,  HttpSession session){
        String account = (String) session.getAttribute("account");
        param.setAccount(account);
        System.out.println(param);
        modifyTeam.setTeam(param);
        session.invalidate();
        return "redirect:/";
    }
}
