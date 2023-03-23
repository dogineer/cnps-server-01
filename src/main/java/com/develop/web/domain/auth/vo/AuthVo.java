package com.develop.web.domain.auth.vo;

public class AuthVo {

    private String userid;
    private String userPassword;
    private Role role;

    public AuthVo(String userid, String userPassword, Role role) {
        this.userid = userid;
        this.userPassword = userPassword;
        this.role = role;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "userid = " + userid + " / " + "userPassword = " + userPassword + " / " + "role = " + role;
    }
}
