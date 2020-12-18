package com.example.web.security;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author : Zhuang Jialong
 * @description :
 * @date : 2020/12/17 下午 5:13
 * @Copyright: Copyright(c)2020 doyokii/market-webplat All Rights Reserved
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}