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
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.AuthorizationRequest;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.View;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import org.springframework.web.util.HtmlUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
////@Configuration
//public class AuthConfig {
//
//
//    /**
//     * 授权(认证)服务器
//     */
//    @Configuration
//    @EnableAuthorizationServer
//    public class AuthrizationServerConfig extends AuthorizationServerConfigurerAdapter {
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
//                    .withClient("my1")
//                    .secret("my1")
//                    .authorizedGrantTypes("authorization_code", "refresh_token")
////                    .redirectUris("http://localhost:8080/hello")
//                    .scopes("all")
//                    .and()
//                    .withClient("my2")
//                    .secret("my2")
//                    .authorizedGrantTypes("authorization_code", "refresh_token")
////                    .redirectUris("http://localhost:8080/hello")
//                    .scopes("all");
//        }
//
//        @Override
//        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//            super.configure(security);
//            security.tokenKeyAccess("isAuthenticated()");
//        }
//
//        @Autowired
//        UserDetailsService userDetailsService;
//
//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
////        super.configure(endpoints);
////        因为 security 配置和oauth 配置加载顺序的关系 可能会出现 UserDetailsService 找不到，所以这里手动设置
////            endpoints.userDetailsService(userDetailsService);
//            endpoints.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter());
//        }
//
////        @Bean
////        @Primary
////        public DefaultTokenServices tokenServices() {
////            DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
////            defaultTokenServices.setTokenStore(tokenStore());
////            defaultTokenServices.setSupportRefreshToken(true);
////            return defaultTokenServices;
////        }
//
//        @Bean
//        public TokenStore tokenStore() {
//            return new JwtTokenStore(accessTokenConverter());
//        }
//
//        @Bean
//        public JwtAccessTokenConverter accessTokenConverter() {
//            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//            converter.setSigningKey("123");
//            return converter;
//        }
//    }
//
//
//    @Component
//    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http); 默认
////        securityProperties.ignoreEach(http.requestMatchers()::mvcMatchers);
////        http.requestMatchers().mvcMatchers(securityProperties.getIgnore());
////        http.requestMatchers().
////        http.requestMatchers().mvcMatchers(new String[]{""});
//            http.authorizeRequests()
//                    .mvcMatchers("/oauth/token", "/login").permitAll()
//                    .anyRequest().authenticated()
//                    .and().formLogin().permitAll().and().logout().permitAll()
////                .and().httpBasic()
////                .rememberMe()
////                .oauth2Client()
////                .authorizationCodeGrant();
//                    .and()
//                    .csrf().disable();
//        }
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        super.configure(auth);
////        auth.inMemoryAuthentication();
//            auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//        }
//
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
//
//    }
//
//    @RestController
//    @SessionAttributes("authorizationRequest")
//    public class ApprovalEndpoint {
//
//
//        @RequestMapping("/oauth/confirm_access")
//        public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
//            final String approvalContent = createTemplate(model, request);
//            if (request.getAttribute("_csrf") != null) {
//                model.put("_csrf", request.getAttribute("_csrf"));
//            }
//            View approvalView = new View() {
//                @Override
//                public String getContentType() {
//                    return "text/html";
//                }
//
//                @Override
//                public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
//                    response.setContentType(getContentType());
//                    response.getWriter().append(approvalContent);
//                }
//            };
//            return new ModelAndView(approvalView, model);
//        }
//
//        protected String createTemplate(Map<String, Object> model, HttpServletRequest request) {
//            AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
//            String clientId = authorizationRequest.getClientId();
//
//            StringBuilder builder = new StringBuilder();
//            builder.append("<html><body><div style=\"display=none;\"><h1>OAuth Approval</h1>");
//            builder.append("<p>Do you authorize \"").append(HtmlUtils.htmlEscape(clientId));
//            builder.append("\" to access your protected resources?</p>");
//            builder.append("<form id=\"confirmationForm\" name=\"confirmationForm\" action=\"");
//
//            String requestPath = ServletUriComponentsBuilder.fromContextPath(request).build().getPath();
//            if (requestPath == null) {
//                requestPath = "";
//            }
//
//            builder.append(requestPath).append("/oauth/authorize\" method=\"post\">");
//            builder.append("<input name=\"user_oauth_approval\" value=\"true\" type=\"hidden\"/>");
//
//            String csrfTemplate = null;
//            CsrfToken csrfToken = (CsrfToken) (model.containsKey("_csrf") ? model.get("_csrf") : request.getAttribute("_csrf"));
//            if (csrfToken != null) {
//                csrfTemplate = "<input type=\"hidden\" name=\"" + HtmlUtils.htmlEscape(csrfToken.getParameterName()) +
//                        "\" value=\"" + HtmlUtils.htmlEscape(csrfToken.getToken()) + "\" />";
//            }
//            if (csrfTemplate != null) {
//                builder.append(csrfTemplate);
//            }
//
//            String authorizeInputTemplate = "<label><input name=\"authorize\" value=\"Authorize\" type=\"submit\"/></label></form>";
//
//            if (model.containsKey("scopes") || request.getAttribute("scopes") != null) {
//                builder.append(createScopes(model, request));
//                builder.append(authorizeInputTemplate);
//            } else {
//                builder.append(authorizeInputTemplate);
//                builder.append("<form id=\"denialForm\" name=\"denialForm\" action=\"");
//                builder.append(requestPath).append("/oauth/authorize\" method=\"post\">");
//                builder.append("<input name=\"user_oauth_approval\" value=\"false\" type=\"hidden\"/>");
//                if (csrfTemplate != null) {
//                    builder.append(csrfTemplate);
//                }
//                builder.append("<label><input name=\"deny\" value=\"Deny\" type=\"submit\"/></label></form>");
//            }
//
//            builder.append("</div></body></html>");
//
//            return builder.toString();
//        }
//
//        private CharSequence createScopes(Map<String, Object> model, HttpServletRequest request) {
//            StringBuilder builder = new StringBuilder("<ul>");
//            @SuppressWarnings("unchecked")
//            Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ?
//                    model.get("scopes") : request.getAttribute("scopes"));
//            for (String scope : scopes.keySet()) {
//                String approved = "true".equals(scopes.get(scope)) ? " checked" : "";
//                String denied = !"true".equals(scopes.get(scope)) ? " checked" : "";
//                scope = HtmlUtils.htmlEscape(scope);
//
//                builder.append("<li><div class=\"form-group\">");
//                builder.append(scope).append(": <input type=\"radio\" name=\"");
//                builder.append(scope).append("\" value=\"true\"").append(approved).append(">Approve</input> ");
//                builder.append("<input type=\"radio\" name=\"").append(scope).append("\" value=\"false\"");
//                builder.append(denied).append(">Deny</input></div></li>");
//            }
//            builder.append("</ul>");
//            return builder.toString();
//        }
//    }
//}
