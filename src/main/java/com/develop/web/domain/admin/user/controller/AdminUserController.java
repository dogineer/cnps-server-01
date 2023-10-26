package com.develop.web.domain.admin.user.controller;

import com.develop.web.domain.admin.user.dto.UpdateUserInfoDto;
import com.develop.web.domain.admin.user.service.UserManagementService;
import com.develop.web.domain.member.user.dto.Userinfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "어드민 > 인사 관리", description = "인사 / 권한 / 역할")
@Slf4j
@RequestMapping(value = "/s1/api/admin/user")
public class AdminUserController {
    private final UserManagementService userManagementService;

    @GetMapping("/account-info/{account}")
    @Operation(summary = "개인 정보 값", description = "개인 정보를 조회 합니다.")
    public Userinfo userDetails(@PathVariable String account) {
        return userManagementService.findMember(account);
    }

    @PutMapping("/account-info/update")
    @Operation(summary = "개인 정보 수정", description = "개인 정보를 수정 합니다.")
    public void userInfoModify(@RequestBody UpdateUserInfoDto userInfo) {
        userManagementService.modifyUserInfo(userInfo);
    }

    @PutMapping("/access/apply/{account}")
    @Operation(summary = "사용자 요청 승인", description = "승인 플래그")
    public void userAccessModify(@PathVariable String account){
        userManagementService.modifyAccess(account);
    }

    @PutMapping("/leave/{account}")
    @Operation(summary = "사용자 탈퇴", description = "삭제 플래그")
    public void userDeleteFlagModify(@PathVariable String account){
        userManagementService.modifyUserDeleteFlag(account);
    }

    @PutMapping("/delete/{account}")
    @Operation(summary = "사용자 삭제", description = "완전 소거")
    public void userDelete(@PathVariable String account){
        userManagementService.deleteUser(account);
    }

}
