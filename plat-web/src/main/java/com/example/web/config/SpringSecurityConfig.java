package com.example.web.config;

import com.example.web.filter.CustomAuthenticationFilter;
import com.example.web.jwt.JwtAuthenticationProvider;
import com.example.web.security.TokenFilter;
import com.example.web.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Resource
    private TokenFilter tokenFilter;
    @Override
    public void configure(WebSecurity web) {
        // 忽略前端静态资源 css js 等
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用自定义认证组件
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));

        // 设置密码加密方式，验证密码的在这里
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用 BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }

    /**
     * 1）HttpSecurity支持cors。
     * 2）默认会启用CRSF，此处因为没有使用thymeleaf模板（会自动注入_csrf参数），
     * 要先禁用csrf，否则登录时需要_csrf参数，而导致登录失败。
     * 3）antMatchers：匹配 "/" 路径，不需要权限即可访问，匹配 "/user" 及其以下所有路径，
     * 都需要 "USER" 权限
     * 4）配置登录地址和退出地址
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 允许无授权访问 "/login"、"/register" "/register-save"
        // 其他地址的访问均需验证权限
        http
                .cors().and()
                // 由于使用的是JWT，这里不需要csrf，将其禁用
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/loginRest/login").permitAll()

                .antMatchers("/test/**").authenticated()
                .and().formLogin().loginProcessingUrl("/loginRest/login")
                // 跨域预检请求
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .and().logout().permitAll()
;
        http.addFilterAt(customAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }
    /**
     * 注册自定义的UsernamePasswordAuthenticationFilter
     */
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setFilterProcessesUrl("/loginRest/login");
        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }


}