package com.develop.web.global.config;

import com.develop.web.global.exception.exception.CustomException;
import com.develop.web.global.filter.AdminPageRequestRankFilter;
import com.develop.web.global.filter.PageRequestAuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public FilterRegistrationBean<PageRequestAuthFilter> pageRequestAuthFilterRegistrationBean() {
        FilterRegistrationBean<PageRequestAuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new PageRequestAuthFilter());
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.addUrlPatterns("/admin/*", "/user/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminPageRequestRankFilter> adminPageRequestRankFilterRegistrationBean() {
        FilterRegistrationBean<AdminPageRequestRankFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminPageRequestRankFilter());
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        registrationBean.addUrlPatterns("/admin/*");
        return registrationBean;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3001");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().configurationSource(corsConfigurationSource()).and()
            .csrf().disable()        //csrf방지
            .formLogin().disable()    //기본 로그인 페이지 없애기
            .headers().frameOptions().disable();
    }
}
