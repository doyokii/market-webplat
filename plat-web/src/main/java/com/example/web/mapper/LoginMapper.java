package com.example.web.mapper;

import com.example.web.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Repository;

/**
 * @author : Zhuang Jialong
 * @description :
 * @date : 2020/11/30 下午 5:47
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
@Mapper
@Repository
public interface LoginMapper {
    /**
     * @author : Zhuang Jialong
     * @description :
     * @date : 2020/11/30 下午 5:53
     * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
     */
   int insert (UserInfo userInfo);
   UserInfo findByUsername(UserInfo userInfo);
   UserInfo findByUsername(String username);
}
