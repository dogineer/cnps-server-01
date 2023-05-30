package com.develop.web.global.exception.handler;

import com.develop.web.global.exception.code.AuthErrorCode;
import com.develop.web.global.exception.exception.CustomException;
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
        log.error(e.getMessage());

        return "error/view";
    }

    @ExceptionHandler(CustomException.class)
    public String sessionNullException(CustomException e, HttpSession session, Model model) {
        log.error(e.getErrorCode().toString());

        model.addAttribute("error", e.getErrorCode().name());
        model.addAttribute("code", e.getErrorCode().code());
        model.addAttribute("message", e.getErrorCode().getMessage());
        log.error(e.getMessage());

        if (e.getErrorCode() == AuthErrorCode.AUTH_ACCESS_NOT_FOUND){
            session.invalidate();
        }

        return "error/view";
    }
}
