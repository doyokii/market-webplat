package com.example.web.controller;

import com.example.web.entity.UserInfo;
import com.example.web.security.TokenUtils;
import com.example.web.service.impl.LoginService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author : Zhuang Jialong
 * @description : 登录控制
 * @date : 2020/11/28 下午 2:25
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
@RestController
@RequestMapping(value = "/loginRest")
@Api(value = "LoginController|登录控制器")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private TokenUtils tokenUtils;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录")
//    public String login(@RequestParam Map<String, Object> params) {
    public String login(@RequestBody UserInfo user) {
//        String s = new Gson().toJson(params);
//        UserInfo user = new Gson().fromJson(s, UserInfo.class);
        loginService.login(user);
        return tokenUtils.createToken(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "注册")
    public String register(@RequestBody UserInfo user) {
        System.out.println(user.getLoginName());
        loginService.userRegister(user);
        return "ur login name is" + user.getLoginName();
    }

}
