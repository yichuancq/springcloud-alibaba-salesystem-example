package com.example.oauth.config;

import com.example.oauth.config.jdbc.JdbcTokenStores;
import com.example.oauth.service.user.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import javax.sql.DataSource;

/**
 * @description: 授权验证服务的配置类
 * @author: yichuan
 * @create time: 2020/05/31 22:23
 */
@Configuration
@EnableAuthorizationServer
@Order(6)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    /**
     * 用户认证器
     */
    @Autowired
    private UserDetailServiceImpl userDetailsService;


    /**
     * 声明TokenStore实现
     *
     * @return
     */
    @Bean
    public JdbcTokenStores tokenStore() {
        return new JdbcTokenStores(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    /**
     * 声明 ClientDetails实现
     *
     * @return
     */
    @Bean
    public ClientDetailsService detailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /****
     *
     * @return
     */
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }


    /***
     *
     * 对应数据表oauth_client_details
     * SELECT * FROM test_oauth2.oauth_client_details
     * 配置能sso登陆的客户端
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //这个地方指的是从jdbc查出数据来存储
        clients.withClientDetails(clientDetails());
    }

    /***
     * 这个是定义授权的请求的路径的Bean
     * @return
     */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }


    /**
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 指定认证管理器
                .authenticationManager(authenticationManager)
                // 用户账号密码认证
                .userDetailsService(userDetailsService)
                // refresh_token
                .reuseRefreshTokens(false)
                // 指定token存储位置
                .tokenStore(tokenStore());
    }

    /**
     * 创建一个默认的资源服务token
     *
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24 * 7);
        // refresh_token默认30天
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }
}
