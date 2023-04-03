package com.develop.web.domain.page.service;

import com.develop.web.domain.auth.vo.Access;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class PageService {
    /*
     * 리다이렉션 페이지
     * */
    public String redirectPage(String url, HttpSession session){

        System.out.println("redirectPage run!");

        boolean userid = session.getAttribute("userid") == null;
        boolean access = session.getAttribute("access") == Access.allow;

        if (!userid){
            if (access){
                return url;
            }
        }
        return "redirect:/";
    }
}
