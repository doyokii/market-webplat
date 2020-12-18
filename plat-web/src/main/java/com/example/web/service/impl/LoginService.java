package com.example.web.service.impl;

import com.example.web.entity.UserInfo;
import com.example.web.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author : Zhuang Jialong
 * @description :
 * @date : 2020/11/30 下午 5:53
 * @Copyright: Copyright(c)2020 doyokii/market-webplat All Rights Reserved
 */
@Service
public class LoginService {
    /**
     * 加入required属性解决Mapper类自动注入红色下划线问题
     *  @Autowired(required = false)
     * https://blog.csdn.net/qq_36850813/article/details/97792853
     * @Mapper标签为mybtis接口标签 idea无法识别

     * 或在mapper类上加入@Repository
     */
    @Autowired
    private LoginMapper loginMapper;

    public int userRegister(UserInfo userInfo){
        userInfo.setUuid(UUID.randomUUID().toString().replace("-",""));
        encryptPassword(userInfo);
        int insert = loginMapper.insert(userInfo);
        return 1;
    }

    public UserInfo login(UserInfo userInfo){
        return loginMapper.findByUsername(userInfo);
    }

    /**
     * 密码加密
     * @param userInfo
     */
    private void encryptPassword(UserInfo userInfo){
        String password = userInfo.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        userInfo.setPassword(password);
    }

    
}
