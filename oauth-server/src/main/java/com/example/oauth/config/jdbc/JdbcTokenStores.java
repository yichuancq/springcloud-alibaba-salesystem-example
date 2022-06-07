package com.example.oauth.config.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;


/***
 *
 * @author yichuan
 */
@Slf4j
public class JdbcTokenStores extends JdbcTokenStore {

    /**
     * @param dataSource
     */
    public JdbcTokenStores(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 重写jdbcStore readAccessToken方法
     *
     * @param tokenValue
     * @return
     */
    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = null;
        try {
            accessToken = new DefaultOAuth2AccessToken(tokenValue);
        } catch (EmptyResultDataAccessException e) {
            if (log.isInfoEnabled()) {
                log.info("Failed to find access token for token " + tokenValue);
            }
        } catch (IllegalArgumentException e) {
            log.warn("Failed to deserialize access token for " + tokenValue, e);
            removeAccessToken(tokenValue);
        }
        return accessToken;
    }
}