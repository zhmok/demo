package com.zh.demo.auth.comfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

@EnableAuthorizationServer
public class AuthrizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    DataSource dataSource;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
//        数据库存储
//        clients.jdbc(dataSource);
//        内存存储
//        clients.inMemory()
//        oauth2 授权类型 Grant Type 取值
//        authorization_code 授权码类型
//        implicit           隐式授权类型
//        password           资源所有者（即用户）密码类型
//        client_credentials 客户端凭据（客户端ID以及Key）类型
//        refresh_token      通过以上授权获得的刷新令牌来获取新的令牌

        clients.inMemory()
                .withClient("my_client")
                .secret("my_scret")
                .authorizedGrantTypes("authorization_code","password","implicit","refresh_token")
                .redirectUris("http://localhost:8080/test")
                .scopes("all");
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
//        security
    }

    @Autowired
    UserDetailsService userDetailsService;
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        super.configure(endpoints);
//        因为 security 配置和oauth 配置加载顺序的关系 可能会出现 UserDetailsService 找不到，所以这里手动设置
        endpoints.userDetailsService(userDetailsService);

    }
}
