package com.develop.web.global.exception.handler;

import com.develop.web.global.exception.exception.AuthApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpSession;


@Slf4j
@Controller
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleAllException(Exception e, Model model) {
        model.addAttribute("error", e.toString());
        model.addAttribute("message", e.getMessage());

        return "error/view";
    }

    @ExceptionHandler(AuthApiException.class)
    public String sessionNullException(AuthApiException e, HttpSession session, Model model) {
        log.error(e.getErrorCode().toString());

        model.addAttribute("error", e.getErrorCode());
        model.addAttribute("message", e.getErrorCode().getMessage());

        session.invalidate();
        return "error/view";
    }
}
