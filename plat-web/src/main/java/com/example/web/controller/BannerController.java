package com.example.web.controller;

import com.example.web.entity.BannerInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Zhuang Jialong
 * @description : Banner处理controller
 * @date : 2020/12/16 下午 4:00
 * @Copyright: Copyright(c)2020 doyokii/market-webplat All Rights Reserved
 */
@RestController
@RequestMapping(value = "/banner")
@Api(value = "BannerController|Banner控制器")
public class BannerController {
    /**
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ApiOperation(value = "上传banner页")
    public String login(@RequestBody BannerInfo banner) {

        return "success";
    }
}
