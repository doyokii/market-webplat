package com.example.web.security;

import com.example.web.entity.UserInfo;
import com.example.web.mapper.LoginMapper;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Resource
    private LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserInfo user = loginMapper.findByUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode("123"));

        if (null == user) {
            logger.debug("用户为空");
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + "admin"));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
//    private User buildUserFromUserEntity(AppUsers authUsers) {
//        Set<UserRole> userRoles = authUsers.getUserRoles();
//
//        boolean enabled = true;
//        boolean accountNotExpired = true;
//        boolean credentialsNotExpired = true;
//        boolean accountNotLocked = true;
//
//        if (authUsers.getAccountIsActive()) {
//            try {
//                if(authUsers.getAccountExpired()){
//                    accountNotExpired = true;
//                } else if (authUsers.getAccountIsLocked()) {
//                    accountNotLocked = true;
//                } else {
//                    if (containsRole((userRoles), roleBo.findRoleByName("FLEX_ADMIN"))){
//                        accountNotLocked = false;
//                    }
//                }
//            }catch(Exception e){
//                enabled = false;
//                e.printStackTrace();
//            }
//        }else {
//            accountNotExpired = false;
//        }
//        // convert model user to spring security user
//        String username = authUsers.getUsername();
//        String password = authUsers.getPassword();
//
//        List<GrantedAuthority> authorities = buildUserAuthority(userRoles);
//
//        User springUser = new User(username, password,enabled, accountNotExpired, credentialsNotExpired, accountNotLocked, authorities);
//        return springUser;
//    }
}