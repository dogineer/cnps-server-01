package com.develop.web.common.view.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/error")
public class ErrorViewController {

    @GetMapping("/view")
    public String handleErrorView(@RequestParam("error") String error,
                                  @RequestParam("code") String code,
                                  @RequestParam("message") String message,
                                  Model model) {

        model.addAttribute("error", error);
        model.addAttribute("code", code);
        model.addAttribute("message", message);

        return "error/view";
    }
}
