package com.develop.web.domain.program.controller;

import com.develop.web.domain.program.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping(value = "/program")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    /*
    * 프로그램 추가
    * */
    @PostMapping("/add")
    public String add(String name, HttpSession session) {
        programService.add(name);
        return "redirect:/Administrator";
    }
}
