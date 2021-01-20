package com.example.web.controller;

import com.example.web.entity.UserInfo;
import com.example.web.jwt.JwtAuthenticatioToken;
import com.example.web.service.impl.LoginService;
import com.example.web.utils.SecurityUtils;
import com.example.web.vo.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author : Zhuang Jialong
 * @description : 登录控制
 * @date : 2020/11/28 下午 2:25
 * @Copyright: Copyright(c)2020 doyokii/market-webplat All Rights Reserved
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/loginRest")
@Api(value = "LoginController|登录控制器")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    public HttpResult login(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
        return HttpResult.ok(token);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "注册")
    public String register(@RequestBody UserInfo user) {
        System.out.println(user.getLoginName());
        loginService.userRegister(user);
        return "ur login name is" + user.getLoginName();
    }

}
