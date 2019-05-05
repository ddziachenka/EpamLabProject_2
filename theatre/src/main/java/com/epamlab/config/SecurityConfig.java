package com.epamlab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private static final String USER_LOGIN = "user";
    private static final String USER_PASSWORD = "12345";
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "12345";
    private static final String LOGIN_PATH = "/login";
    private static final String FAILURE_URL = "/login?error=true";
    private static final String PATTERN_PATH_ADMIN_ROLE = "/admin/**";
    private static final String PATTERN_PATH_ADMIN_AND_USER_ROLE = "/**";
    private static final String SUCCESS_FROWARD_URL = "/";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(LOGIN_PATH).permitAll()
                .antMatchers(PATTERN_PATH_ADMIN_ROLE).hasRole(ADMIN_ROLE)
                .antMatchers(PATTERN_PATH_ADMIN_AND_USER_ROLE).hasAnyRole(USER_ROLE, ADMIN_ROLE)
                .and().formLogin()
                .loginPage(LOGIN_PATH)
                .failureUrl(FAILURE_URL)
                .successForwardUrl(SUCCESS_FROWARD_URL)
                .passwordParameter(PASSWORD)
                .usernameParameter(USERNAME)
                .permitAll()
                .and().logout().logoutSuccessUrl(LOGIN_PATH).permitAll()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(USER_LOGIN).password(USER_PASSWORD).roles(USER_ROLE)
                .and()
                .withUser(ADMIN_LOGIN).password(ADMIN_PASSWORD).roles(ADMIN_ROLE);
    }
}