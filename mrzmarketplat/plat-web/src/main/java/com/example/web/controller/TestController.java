package com.example.web.controller;

import com.example.web.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author : Zhuang Jialong
 * @description :
 * @date : 2020/12/16 下午 4:00
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
@RestController
@RequestMapping(value = "/test")
@Api(value = "测试接口拦截，验证token过滤|测试控制器")
public class TestController {
    @RequestMapping(value = "/mrz", method = RequestMethod.POST)
    @ApiOperation(value = "测试接口")
    public String login(@RequestBody UserInfo user) {
        System.out.println("[===============程序执行到此===============]");
        return "success";
    }
}
