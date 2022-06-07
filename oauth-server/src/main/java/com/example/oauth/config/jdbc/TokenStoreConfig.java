package com.example.oauth.config.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/***
 *
 * @author yichuan
 */
@Configuration
public class TokenStoreConfig {
    @Autowired
    private DataSource dataSource;

    @Bean(name = "myMemoryTokenStore")
    public TokenStore myMemoryTokenStore() {
        return new JdbcTokenStores(dataSource);
    }
}