package com.develop.web.domain.utils;

import com.develop.web.domain.auth.vo.AuthVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtTokenUtil {
    private String SECRET_KEY = "dddddddddsdaddasfbksabfsaasdsadsadsadsadasdsadasdasdasdsadsadsadasdas";

    protected void init() {
        // 객체 초기화, SECRET_KEY를 Base64로 인코딩한다.
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    // Jwt 토큰 생산
    public String createToken(AuthVo authVo) {
        long expTime = 30 * 60 * 1000L;

        if (expTime <= 0) {
            throw new RuntimeException("만료 시간이 0보다 커야합니다.");
        }

        Claims claims = Jwts.claims().setSubject("access_token");

        claims.put("key", "value");

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(authVo.getUserid())
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    //토큰 회원 정보 추출
    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
