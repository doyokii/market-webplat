package com.example.web.security;

import com.example.web.entity.UserInfo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Zhuang Jialong
 * @description : Jwt 拦截器，通过Token鉴权
 * @date : 2020/12/3 下午 2:02
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class TokenFilter extends OncePerRequestFilter {

    @Resource
    TokenUtils tokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        // 存储Token的Headers Key与Value
        final String authorizationKey = "Authorization";
        String authorizationValue;
        try {
            authorizationValue = request.getHeader(authorizationKey);
        } catch (Exception e) {
            authorizationValue = null;
        }
        // Token 开头部分
        String bearer = "Bearer ";
        if (authorizationValue != null && authorizationValue.startsWith(bearer)) {
            // token
            String token = authorizationValue.substring(bearer.length());

            UserInfo userInfo = tokenUtils.validationToken(token);
            if (userInfo != null) {
                // Spring Security 角色名称默认使用 "ROLE_" 开头
                // authorities.add 可以增加多个用户角色，对于一个用户有多种角色的系统来说，
                // 可以通过增加用户角色表、用户--角色映射表，存储多个用户角色信息
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + userInfo.getRoleIds()));
                // 传入用户名、用户密码、用户角色。 这里的密码随便写的，用不上
                UserDetails userDetails = new User(userInfo.getUsername(), "password", authorities);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(userDetails.getUsername());
                // 授权
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    
}