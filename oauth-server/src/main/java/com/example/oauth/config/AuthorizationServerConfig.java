package com.example.oauth.config;

import com.example.oauth.config.error.MssWebResponseExceptionTranslator;
import com.example.oauth.config.jdbc.JdbcTokenStores;
import com.example.oauth.service.user.UserDetailServiceImpl;
import com.example.oauth.util.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import javax.sql.DataSource;

/**
 * @calss name AuthorizationServerConfig
 * @description:
 * @author: yichuan
 * @create time: 2020/05/31 22:23
 */
@Configuration
@EnableAuthorizationServer
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

    /**
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new MssWebResponseExceptionTranslator();
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

//        clients.withClientDetails(clientDetails());


        clients.inMemory().withClient("android")
                //.authorizedGrantTypes("client_credentials")
                .scopes("all")
                .secret(DigestUtil.encrypt("android"))
                .authorities("ROLE_ADMIN", "ROLE_USER")
                .and()
                .withClient("browser")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .scopes("all")
                .accessTokenValiditySeconds(60 * 60 * 24 * 7)
                .refreshTokenValiditySeconds(60 * 60 * 24 * 7)
                .authorities("ROLE_ADMIN", "ROLE_USER")
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("all");


//        clients.inMemory()
//                .withClient("android")
//                //定义访问的作用域
//                .scopes("all")
//                // 加密
//                .secret(DigestUtil.encrypt("android"))
//                // 支持的授权模式 密码模式
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .and().withClient("webapp")
//                .scopes("all")
//                .authorizedGrantTypes("implicit")
//                .and().withClient("browser")
//                .authorizedGrantTypes("refresh_token", "password")
//                .scopes("all");
    }


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
        //.tokenServices(defaultTokenServices());
    }

//    @Primary
//    @Bean
//    public DefaultTokenServices defaultTokenServices() {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(tokenStore());
//        tokenServices.setSupportRefreshToken(true);
//        // token有效期自定义设置，默认12小时
//        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24 * 7);
//        // refresh_token默认30天
//        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24 * 7);
//        return tokenServices;
//    }

}
