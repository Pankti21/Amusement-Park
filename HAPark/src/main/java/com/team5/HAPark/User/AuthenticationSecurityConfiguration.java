package com.team5.HAPark.User;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//I referenced these sources to create this class:
//https://www.baeldung.com/spring-security-multiple-auth-providers
//https://docs.spring.io/spring-security/site/docs/3.2.4.RELEASE/reference/htmlsingle/#csrf-logout
@EnableWebSecurity
public class AuthenticationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new Authenticator());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().
                    antMatchers("/rides").permitAll().
                    antMatchers("/rides/*").permitAll().
                    antMatchers("/test").permitAll().
                    antMatchers("/testdb").permitAll().
                    antMatchers("/ridetest").permitAll().
                    antMatchers(HttpMethod.GET,"/menu/**").permitAll().
                    antMatchers(HttpMethod.GET,"/tickets/**").permitAll().
                    antMatchers("/Main").permitAll().
                    antMatchers("/reserve").permitAll().
                    antMatchers("/reserved").permitAll().
                  antMatchers("/**").authenticated()
                  .and().formLogin();
          http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}


