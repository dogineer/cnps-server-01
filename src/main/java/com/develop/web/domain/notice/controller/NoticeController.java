package com.develop.web.domain.notice.controller;

import com.develop.web.domain.notice.service.PostListFetcher;
import com.develop.web.domain.notice.dto.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "공지사항", description = "")
@RequestMapping(value = "/notice")
public class NoticeController {

    private final PostListFetcher postListFetcher;

    @PostMapping(value = "/create")
    @Operation(summary = "공지사항 작성", description = "공지사항을 작성합니다.")
    public String createNotice(PostDto writer, HttpSession session){
        Integer empId = session.getAttribute("empId").hashCode();
        writer.setEmpId(empId);

        postListFetcher.setPost(writer);
        return "redirect:/management/employee";
    }

    @GetMapping(value = "/list")
    @Operation(summary = "공지사항 목록", description = "공지사항 데이터를 받아옵니다.")
    public List<PostDto> Post(){
        return postListFetcher.getPost();
    }
}
