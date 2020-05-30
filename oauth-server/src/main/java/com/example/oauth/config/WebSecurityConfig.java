package com.example.oauth.config;

import com.example.oauth.handler.CustomAccessDeniedHandler;
import com.example.oauth.handler.CustomAuthenticationFailureHandler;
import com.example.oauth.handler.CustomPermissionEvaluator;
import com.example.oauth.handler.CustomerSavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @author yichuan
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomerSavedRequestAwareAuthenticationSuccessHandler successHandler;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * @return
     */
    @Autowired
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }

    /**
     * 静态资源设置
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**", "/login");
    }

    /**
     * 认证
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 密码加密算法
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }


    /**
     * http请求设置
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/oauth/**")
                .and().authorizeRequests()
                .antMatchers("/oauth/**")
                .authenticated().and().csrf().disable();
        //
        http.authorizeRequests()
                //不拦截登录相关方法
                .antMatchers("/login/**", "/login")
                .permitAll()
                //排除过滤actuator，结合服务注册中心
                .antMatchers("/actuator/**", "/actuator")
                .permitAll()
                //openUserEdit
                // swagger start
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                //swagger end
                // .anyRequest() 只能用一次，在 security 下
                //.anyRequest()
                //.authenticated()// 任何尚未匹配的URL只需要验证用户即可访问
                .anyRequest()
                //其他所有资源都需要登陆后才能访问
                //根据账号权限访问
                .access("@rbacPermission.hasPermission(request, authentication)")
                .and()
                .formLogin()
                .loginPage("/login")
                //登录用户名参数
                .usernameParameter("username")
                //登录密码参数
                .defaultSuccessUrl("/home").permitAll()
                .passwordParameter("password")
                .failureUrl("/error").permitAll()
//               /设置默认登录成功跳转页面
                //登录成功处理器
                .successHandler(successHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .and()
                .logout().permitAll()
                .and().csrf().disable();

    }
}
