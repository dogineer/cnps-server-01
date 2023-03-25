package com.develop.web.domain.auth.vo;

public class AuthVo {

    private String userid;
    private String password;
    private Role role;

    public AuthVo(String userid, String password, Role role) {
        this.userid = userid;
        this.password = password;
        this.role = role;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "userid = " + userid + " / " + "password = " + password + " / " + "role = " + role;
    }
}
