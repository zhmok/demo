package com.zh.demo.auth.comfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityProperties securityProperties;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
//        securityProps.ignoreEach(web.ignoring()::mvcMatchers);
//        web.ignoring().antMatchers("/v2/api-docs",
//                "/configuration/ui",
//                "/swagger-resources/**",
//                "/configuration/security",
//                "/swagger-ui.html",
//                "/webjars/**");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.inMemoryAuthentication();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); 默认
//        securityProperties.ignoreEach(http.requestMatchers()::mvcMatchers);
//        http.requestMatchers().mvcMatchers(securityProperties.getIgnore());
//        http.requestMatchers().
//        http.requestMatchers().mvcMatchers(new String[]{""});
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().and()
//                .formLogin().permitAll().and().logout().permitAll()
//                .and()
                .rememberMe();
//                .oauth2Client()
//                .authorizationCodeGrant()
//                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        manager.createUser(User.builder()
                .username("123")
                .password(bCryptPasswordEncoder.encode("123"))
//                .password("{noop}123")
                .roles("USER")
                .passwordEncoder((v) -> {
                    return bCryptPasswordEncoder.encode(v);
                })
                .build());
        return manager;
    }


}