package com.example.web.security;

import com.example.web.entity.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


/**
 * 生成 验证用户Token
 *
 * @author xian
 */
@Component
public class TokenUtils implements Serializable {
    private static final long serialVersionUID = -3L;
    /**
     * Token 有效时长
     */
    private static final Long EXPIRATION = 604800L;

    /**
     * 生成 Token 字符串  setAudience 接收者 setExpiration 过期时间 role 用户角色
     *
     * @param userInfo 用户信息
     * @return 生成的Token字符串 or null
     */
    public String createToken(UserInfo userInfo) {
        try {
            // Token 的过期时间
            Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
            // 生成 Token
            String token = Jwts.builder()
                    // 设置 Token 签发者 可选
                    .setIssuer("SpringBoot")
                    // 根据用户名设置 Token 的接受者
                    .setAudience(userInfo.getUsername())
                    // 设置过期时间
                    .setExpiration(expirationDate)
                    // 设置 Token 生成时间 可选
                    .setIssuedAt(new Date())
                    // 通过 claim 方法设置一个 key = role，value = userRole 的值
                    .claim("role", userInfo.getRoleIds())
                    // 设置加密密钥和加密算法，注意要用私钥加密且保证私钥不泄露
                    .signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey())
                    .compact();
            return String.format("Bearer %s", token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 验证 Token ，并获取到用户名和用户权限信息
     *
     * @param token Token 字符串
     * @return sysUser 用户信息
     */
    public UserInfo validationToken(String token) {
        try {
            // 解密 Token，获取 Claims 主体
            Claims claims = Jwts.parserBuilder()
                    // 设置公钥解密，以为私钥是保密的，因此 Token 只能是自己生成的，如此来验证 Token
                    .setSigningKey(RsaUtils.getPublicKey())
                    .build().parseClaimsJws(token).getBody();
            assert claims != null;
            // 验证 Token 有没有过期 过期时间
            Date expiration = claims.getExpiration();
            // 判断是否过期 过期时间要在当前日期之后
            if (!expiration.after(new Date())) {
                return null;
            }
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(claims.getAudience());
            userInfo.setRoleIds(Arrays.asList(claims.get("role").toString()));
            return userInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}