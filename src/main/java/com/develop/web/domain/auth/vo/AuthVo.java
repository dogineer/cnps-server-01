package com.develop.web.domain.auth.vo;

public class AuthVo {

    private String userid;
    private String userPassword;

    public AuthVo(String userid, String userPassword) {
        this.userid = userid;
        this.userPassword = userPassword;
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

    public void setUserpassword(String userpassword) {
        this.userPassword = userpassword;
    }

    @Override
    public String toString() {
        return "userid = " + userid + " / " + "userpassword = " + userPassword;
    }
}
