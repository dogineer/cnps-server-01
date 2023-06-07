package com.develop.web.domain.notice.controller;

import com.develop.web.domain.notice.service.DeleteNotice;
import com.develop.web.domain.notice.service.PostListFetcher;
import com.develop.web.domain.notice.dto.NoticeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "공지사항", description = "")
@RequestMapping(value = "/notice")
public class NoticeController {

    private final PostListFetcher postListFetcher;
    private final DeleteNotice deleteNotice;

    @PostMapping(value = "/create")
    @Operation(summary = "공지사항 작성", description = "공지사항을 작성합니다.")
    public String createNotice(NoticeDto writer, HttpSession session){
        Integer empId = session.getAttribute("empId").hashCode();
        writer.setEmpId(empId);

        postListFetcher.setPost(writer);
        return "redirect:/management/employee";
    }

    @GetMapping(value = "/list")
    @Operation(summary = "공지사항 목록", description = "공지사항 데이터를 받아옵니다.")
    public List<NoticeDto> Post(){
        return postListFetcher.getPost();
    }

    @DeleteMapping("/delete/{noticeId}")
    @Operation(summary = "공지사항 삭제", description = "공지사항 id를 입력하여 삭제합니다.")
    public void deleteDept(@PathVariable Integer noticeId){
        deleteNotice.noticeId(noticeId);
    }
}
