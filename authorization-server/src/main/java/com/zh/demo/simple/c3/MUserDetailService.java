package com.zh.demo.simple.c3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MUserDetailService  implements UserDetailsService {
    @Autowired
    private PasswordEncoder p;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);
        // 根据用户名查找用户信息

        // 数据库密码是
        String password = p.encode("123");
        System.out.println("数据库密码是 "+p.encode("123"));
        UserDetails user = User.builder()
                .username("123")
                .password(password)
//                .password("123")
//                .roles("USER")
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"))
//                .accountLocked(true)
//                .roles("ROLE_USER")
//                .passwordEncoder((v) -> bCryptPasswordEncoder.encode(v))
                .build();
        return user;
    }
}
