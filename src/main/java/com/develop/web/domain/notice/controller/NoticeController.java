package com.develop.web.domain.notice.controller;

import com.develop.web.domain.notice.service.PostListFetcher;
import com.develop.web.domain.notice.dto.PostDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

    private final PostListFetcher postListFetcher;

    public NoticeController(PostListFetcher postListFetcher) {
        this.postListFetcher = postListFetcher;
    }

    /*
    * 공지사항 글 작성
    * */
    @PostMapping(value = "/create")
    public String createNotice(PostDto writer, HttpSession session){
        Integer empId = session.getAttribute("empId").hashCode();
        writer.setEmpId(empId);

        postListFetcher.setPost(writer);
        return "redirect:/management/employee";
    }

    /*
    * 공지사항 리스트 받아오기
    * */
    @PostMapping(value = "/list")
    public String Post(){

        postListFetcher.getPost();
        return "redirect:/Administrator/employee";
    }
}
