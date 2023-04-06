package com.develop.web.domain.media.controller;

import com.develop.web.domain.media.service.IngestService;
import com.develop.web.domain.media.vo.IngestRequestData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/ingest")
public class IngestController {
    private final IngestService ingestService;

    public IngestController(IngestService ingestService) {
        this.ingestService = ingestService;
    }


    @PostMapping(value = "/request")
    public String ingestRequest(IngestRequestData data){
        ingestService.IngestRequest(data);

        return "redirect:/home";
    }
}
