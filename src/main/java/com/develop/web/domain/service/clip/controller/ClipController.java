package com.develop.web.domain.service.clip.controller;

import com.develop.web.domain.service.clip.dto.ClipDto;
import com.develop.web.domain.service.clip.service.ClipDataListFetcher;
import com.develop.web.domain.service.clip.service.XmlShow;
import com.develop.web.domain.service.page.dto.CriteriaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        CriteriaDto criteriaDto = new CriteriaDto();
        return clipDataListFetcher.getClipList(criteriaDto);
    }

    @GetMapping("/xml")
    @Operation(summary = "클립 xml 데이터 확인", description = "테스트중")
    public String showXml(@RequestParam("file") String file) throws ParserConfigurationException, TransformerException {
        return xmlShow.create("test", file);
    }

    @GetMapping("/display")
    @Operation(summary = "클립 프리뷰", description = "로컬에 저장된 클립을 재생합니다.")
    public ResponseEntity<StreamingResponseBody> display(@RequestParam("filename") String filename) {
        try {
            File videoFile = new File(filename);

            if (videoFile.exists()) {
                String contentType = findDetermineContentType(filename);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(contentType));
                headers.setContentDispositionFormData("attachment", filename);

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(outputStream -> {
                            try (InputStream inputStream = new FileInputStream(videoFile)) {
                                byte[] buffer = new byte[64 * 1024]; // 64KB 버퍼
                                int bytesRead;

                                while ((bytesRead = inputStream.read(buffer)) != -1) {
                                    try {
                                        outputStream.write(buffer, 0, bytesRead);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        break;
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    private String findDetermineContentType(String filename) {
        String extension = filename.substring(filename.lastIndexOf('.') + 1);

        switch (extension.toLowerCase()) {
            case "mp4":
                return "video/mp4";
            case "mov":
                return "video/quicktime";

            default:
                return "application/octet-stream";
        }
    }
}
