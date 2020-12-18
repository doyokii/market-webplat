package com.example.web.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : Zhuang Jialong
 * @description : 身份验证提供者
 * @date : 2020/12/17 下午 5:14
 * @Copyright: Copyright(c)2020 doyokii/market-webplat All Rights Reserved
 */
public class JwtAuthenticationProvider extends DaoAuthenticationProvider {

    public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	// 可以在此处覆写整个登录认证逻辑
    	return super.authenticate(authentication);

    }
    
    @Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
    	// 可以在此处覆写密码验证逻辑
		super.additionalAuthenticationChecks(userDetails, authentication);
	}

}