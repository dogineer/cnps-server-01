package com.develop.web.domain.auth.api;

import com.develop.web.domain.utils.JwtTokenUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class TestTokenApi {

    private final JwtTokenUtil jwtTokenUtil;

    public TestTokenApi(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/create/token")
    public Map<String, Object> createToken(
            @RequestParam(value = "subject") String subject) {
        String token = jwtTokenUtil.createToken(subject);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", token);
        System.out.println("token = "+map);
        return map;
    }

    @GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam(value = "token") String token) {
        String subject = jwtTokenUtil.getSubject(token);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", subject);

        return map;
    }
}
