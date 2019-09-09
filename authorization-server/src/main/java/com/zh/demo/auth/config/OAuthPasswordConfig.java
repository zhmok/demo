//package com.zh.demo.auth.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class OAuthPasswordConfig {
//
//    /**
//     * 授权(认证)服务器
//     */
//    @Configuration
//    @EnableAuthorizationServer
//    public static class AuthrizationServerConfig extends AuthorizationServerConfigurerAdapter {
////        @Resource
////        DataSource dataSource;
//
//
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//            super.configure(clients);
////        数据库存储
////        clients.jdbc(dataSource);
////        内存存储
////        clients.inMemory()
////        oauth2 授权类型 Grant Type 取值
////        authorization_code 授权码类型
////        implicit           隐式授权类型
////        password           资源所有者（即用户）密码类型
////        client_credentials 客户端凭据（客户端ID以及Key）类型
////        refresh_token      通过以上授权获得的刷新令牌来获取新的令牌
//
//            clients.inMemory()
//                    .withClient("m1")
//                    .secret("m 1")
//                    .authorizedGrantTypes("password", "refresh_token")
//                    .scopes("all")
//                    .and()
//                    .withClient("my2")
//                    .secret("my2")
//                    .authorizedGrantTypes("password", "refresh_token")
//                    .scopes("all");
//        }
//
//        @Override
//        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//            super.configure(security);
//            security.tokenKeyAccess("isAuthenticated()");
//        }
//
////        @Autowired
////        UserDetailsService userDetailsService;
//
////        @Autowired
////        AuthenticationManager authenticationManager;
//
//        @Autowired
//        UserDetailsService userDetailsService;
//
//
//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
////        super.configure(endpoints);
////        因为 security 配置和oauth 配置加载顺序的关系 可能会出现 UserDetailsService 找不到，所以这里手动设置
//            endpoints.userDetailsService(userDetailsService);
////            endpoints.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter());
////            endpoints.authenticationManager(authenticationManager);
//        }
////
////        @Bean
////        public TokenStore tokenStore() {
////            return new JwtTokenStore(accessTokenConverter());
////        }
////
////        @Bean
////        public JwtAccessTokenConverter accessTokenConverter() {
////            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
////            converter.setSigningKey("123");
////            return converter;
////        }
//    }
//
//    @Configuration
//    @EnableResourceServer
//    public static class ResourceServer extends ResourceServerConfigurerAdapter {
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests()
//                    .anyRequest()
//                    .authenticated()
//                    .and()
//                    .requestMatchers()
//                    .antMatchers("/api/**");
//        }
//    }
//
//    @Configuration
//    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
////        @Bean
////        @Override
////        public AuthenticationManager authenticationManagerBean() throws Exception {
////            return super.authenticationManagerBean();
////        }
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        super.configure(auth);
////        auth.inMemoryAuthentication();
//            auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//        }
////
//        @Bean
//        public PasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//
//        @Bean
//        @Override
//        public UserDetailsService userDetailsService() {
//            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//            manager.createUser(User.builder()
//                    .username("123")
////                .password(bCryptPasswordEncoder.encode("123"))
////                .password("{noop}123")
//                    .password("123")
//                    .roles("USER")
////                .roles("ROLE_USER")
//                    .passwordEncoder((v) -> bCryptPasswordEncoder.encode(v))
//                    .build());
//            return manager;
//        }
//    }
//
////    @Component
////    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////////        @Override
////////        protected void configure(HttpSecurity http) throws Exception {
//////////        super.configure(http); 默认
//////////        securityProperties.ignoreEach(http.requestMatchers()::mvcMatchers);
//////////        http.requestMatchers().mvcMatchers(securityProperties.getIgnore());
//////////        http.requestMatchers().
//////////        http.requestMatchers().mvcMatchers(new String[]{""});
////////            http.authorizeRequests()
////////                    .mvcMatchers("/oauth/token", "/login").permitAll()
////////                    .anyRequest().authenticated()
////////
//////////                .and().httpBasic()
//////////                .rememberMe()
//////////                .oauth2Client()
//////////                .authorizationCodeGrant();
////////                    .and()
////////                    .csrf().disable();
////////        }
////////
////        @Override
////        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//////        super.configure(auth);
//////        auth.inMemoryAuthentication();
////            auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
////        }
//////
////        @Bean
////        public PasswordEncoder passwordEncoder() {
////            return new BCryptPasswordEncoder();
////        }
////
////        @Bean
////        @Override
////        public UserDetailsService userDetailsService() {
////            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////
////            manager.createUser(User.builder()
////                    .username("123")
//////                .password(bCryptPasswordEncoder.encode("123"))
//////                .password("{noop}123")
////                    .password("123")
////                    .roles("USER")
//////                .roles("ROLE_USER")
////                    .passwordEncoder((v) -> bCryptPasswordEncoder.encode(v))
////                    .build());
////            return manager;
////        }
////////
////    }
//
//
//}
