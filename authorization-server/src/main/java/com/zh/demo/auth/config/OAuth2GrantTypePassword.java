//package com.zh.demo.auth.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Security OAuth2 认证
// * Grant Type 密码模式
// * 配置流程:
// * 1.需要 spring-boot-starter-security 和 spring-cloud-starter-oauth2 依赖
// * 2.配置认证服务器
// * 3.配置资源服务器
// * 3.web security 公共配置
// *
// * 以测试 可以正常配置运行
// */
//@Configuration
//public class OAuth2GrantTypePassword {
//
//    /**
//     * 认证服务器配置
//     */
//    @Configuration
//    @EnableAuthorizationServer
//    public class OAuth2AuthorizationServer extends
//            AuthorizationServerConfigurerAdapter {
//        @Override
//        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//            super.configure(security);
////            security.tokenKeyAccess("isAuthenticated()");
//        }
//
//        @Autowired
//        AuthenticationManager authenticationManager;
//
//        /**
//         * 这里设置用户业务的实现
//         * @param endpoints
//         */
//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//            endpoints.authenticationManager(authenticationManager).userDetailsService(username -> {
//                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//                return User.builder()
//                        .username(username)
//                        .passwordEncoder(v -> bCryptPasswordEncoder.encode(v))
//                        .password(bCryptPasswordEncoder.encode("123"))
//                        .roles("ADMIN")
//                        .build();
//            });
//        }
//
//        @Autowired
//        BCryptPasswordEncoder bCryptPasswordEncoder;
//
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients)
//                throws Exception {
//            clients.inMemory()
//                    .withClient("m1")
//                    .secret(bCryptPasswordEncoder.encode("m1"))
//                    .authorizedGrantTypes("password")
//                    .scopes("all")
//            .and()
//                    .withClient("m2")
//                    .secret(bCryptPasswordEncoder.encode("m2"))
//                    .authorizedGrantTypes("password")
//                    .scopes("all");
//        }
//
//    }
//
//    /**
//     * 资源服务器配置
//     */
//    @Configuration
//    @EnableResourceServer
//    public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            SMSFilter smsFilter = new SMSFilter();
////            http.addFilterBefore(smsFilter, UsernamePasswordAuthenticationFilter.class)
//            http.authorizeRequests()
//                    .anyRequest().authenticated()
//                    .mvcMatchers("/oauth/token").permitAll()
//                .and().sessionManagement()
//                    .maximumSessions(1).sessionRegistry(sessionRegistry())//.maxSessionsPreventsLogin(true)
////            .maxSessionsPreventsLogin(true)
//            ;
////                    .and().mvcMatcher("/api/**");
//
//        }
//
//        @Bean
//        public SessionRegistry sessionRegistry(){
//            SessionRegistryImpl sessionRegistry = new SessionRegistryImpl();
//
//            return sessionRegistry;
//        }
//    }
//
//    /**
//     * security 公共配置
//     */
//    @Configuration
//    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//        private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//        @Bean
//        @Override
//        public AuthenticationManager authenticationManagerBean() throws Exception {
//            return super.authenticationManagerBean();
//        }
//
//        /**
//         * 配置密码加密
//         *
//         * @return
//         */
//        @Bean
//        public BCryptPasswordEncoder passwordEncoder() {
//            return bCryptPasswordEncoder;
//        }
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(new UserDetail()).passwordEncoder(passwordEncoder());
//        }
//    }
//
//
//    /**
//     * 配置实现 登录的业务
//     */
//    public class UserDetail implements UserDetailsService {
//        @Override
//        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//            System.out.println(username);
//            return User.builder()
//                    .username(username)
////                    .passwordEncoder(v->bCryptPasswordEncoder.encode(v))
//                    .password(bCryptPasswordEncoder.encode("123"))
//                    .roles("ADMIN")
//                    .build();
//        }
//    }
//
//    /**
//     * 这里配置短信登录的过滤器
//     * 这里知识配置了方式
//     */
//    public class SMSFilter extends OncePerRequestFilter {
//        @Override
//        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        }
//    }
//}
