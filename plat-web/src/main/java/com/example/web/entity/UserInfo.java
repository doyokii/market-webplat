package com.example.web.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Zhuang Jialong
 * @description : 用户信息
 * @date : 2020/11/24 下午 2:06
 * @Copyright: Copyright(c)2020 doyokii/market-webplat All Rights Reserved
 */
@Data
public class UserInfo extends CommonEntity {
    /**
     * 超级管理员
     */
    public static final Integer SUPER_USER = 0;
    /**
     * 业务用户
     */
    public static final Integer BUSINESS_USER = 1;

    /**
     * 登录名
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String userPhone;

    /**
     * 用户类型(0：, 1：, 2:)
     */
    private Integer userType;
    /**
     * 禁用状态(0：未禁用,1：已禁用)
     */
    private Integer disabledStatus;
    /**
     * 角色ID集合
     */
    private List<String> roleIds;
    /**
     * 角色名称
     */
    private String roleNames;

}
