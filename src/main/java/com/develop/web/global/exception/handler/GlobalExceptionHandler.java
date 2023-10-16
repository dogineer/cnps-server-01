package com.develop.web.global.exception.handler;

import com.develop.web.global.exception.code.AuthErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import com.develop.web.global.exception.response.ErrorResponse;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


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
    public void sessionNullException(CustomException e, HttpServletResponse response, HttpSession session) throws IOException {
        log.error("[Error] Code: " + e.getErrorCode());
        log.error("[Error] Message: " + e.getMessage());

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("errorCode", e.getErrorCode().name());
        errorResponse.addProperty("errorMessage", e.getErrorCode().getMessage());

        try (PrintWriter writer = response.getWriter()) {
            writer.write(errorResponse.toString());
        }

        if (e.getErrorCode() == AuthErrorCode.AUTH_ACCESS_NOT_FOUND){
            session.invalidate();
        }
    }

    @ExceptionHandler(IOException.class)
    public void handleIOException(IOException e) {
        log.error(e.getMessage());
    }
}
