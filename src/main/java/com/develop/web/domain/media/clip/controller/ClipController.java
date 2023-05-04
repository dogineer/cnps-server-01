package com.develop.web.domain.media.clip.controller;

import com.develop.web.domain.media.clip.dto.ClipDto;
import com.develop.web.domain.media.clip.service.ClipDataListFetcher;
import com.develop.web.domain.media.clip.service.XmlShow;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Tag(name = "클립", description = "클립")
@RequestMapping(value = "/clip")
@RestController
public class ClipController {
    private final ClipDataListFetcher clipDataListFetcher;
    private final XmlShow xmlShow;

    @GetMapping("/list")
    @Operation(summary = "클립 리스트 확인", description = "서버에 저장된 클립 데이터 목록 확인")
    public List<ClipDto> showClipDataList(){
        return clipDataListFetcher.getClipList();
    }

    @GetMapping("/xml")
    @Operation(summary = "클립 xml 데이터 확인", description = "테스트중")
    public String showXml() throws ParserConfigurationException, TransformerException {
        return xmlShow.create("test", "/Users/boseongpark/Movies/Video Source/[MV] BTS (방탄소년단) 'FAKE LOVE' Official MV.mp4");
    }
}
