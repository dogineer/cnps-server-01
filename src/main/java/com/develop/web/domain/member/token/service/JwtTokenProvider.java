package com.develop.web.domain.member.token.service;

import com.develop.web.domain.member.token.dto.JwtToken;
import com.develop.web.global.exception.code.TokenErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    String secretKey;

    private static final String AUTHORITIES_KEY = "nps-auth";
    private static final String BEARER_TYPE = "Bearer";
    //밀리초 * 초 * 분 * 시 * 일
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60;   // 1시간
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 2;  // 2주

    /**
     * @description 시크릿키 로드
     */
    private Key getSigninKey(){
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        return Keys.hmacShaKeyFor(secretByteKey);
    }

    /**
     * @description 토큰 정보 확인
     */
    public Claims getJwtContents(String accessToken) {
        return parseClaims(accessToken);
    }

    /**
     * @description 클레임 페이로드 정보 값
     */
    public Claims payload(String name, String email) {
        Claims claims = Jwts.claims();
        claims.put("name", name);
        claims.put("email", email);
        return claims;
    }

    /**
     * @description 토큰 생성
     */
    public JwtToken generateToken(Authentication authentication) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT"); // Type 설정
        headers.put("alg", "HS256");

        String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        String accessToken = Jwts.builder()
            .setHeader(headers)
            .setSubject(AUTHORITIES_KEY)
            .claim("account", authentication.getName())
            .claim("role", authorities)
            .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
            .signWith(getSigninKey(), SignatureAlgorithm.HS256)
            .compact();

        String refreshToken = Jwts.builder()
            .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
            .signWith(getSigninKey(), SignatureAlgorithm.HS256)
            .compact();

        return JwtToken.builder()
            .grantType(BEARER_TYPE)
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    /**
     * @description 인증 받기
     */
    public Authentication getAuthentication(String accessToken) throws CustomException {

        Claims claims = parseClaims(accessToken);

        if (claims.get("role") == null) {
            throw new CustomException(TokenErrorCode.TOKEN_NO_ACCESS);
        }

        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get("role").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * @description 토큰 유효성 검사
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info(TokenErrorCode.INVALID_JWT_TOKEN.getMessage());
            throw new CustomException(TokenErrorCode.INVALID_JWT_TOKEN);
        } catch (ExpiredJwtException e) {
            log.warn(TokenErrorCode.EXPIRED_JWT_TOKEN.getMessage());
            throw new CustomException(TokenErrorCode.EXPIRED_JWT_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.warn(TokenErrorCode.UNSUPPORTED_JWT_TOKEN.getMessage());
            throw new CustomException(TokenErrorCode.UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalArgumentException e) {
            log.warn(TokenErrorCode.JWT_CLAIMS_STRING_IS_EMPTY.getMessage());
            throw new CustomException(TokenErrorCode.JWT_CLAIMS_STRING_IS_EMPTY);
        }
    }

    /**
     * @description 클레임 분석
     */
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
