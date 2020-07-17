package com.studymouse.studymouseserver.security;

import com.studymouse.studymouseserver.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;


@Configuration
public class SecurityConfig {

    @RequiredArgsConstructor
    @EnableGlobalMethodSecurity(securedEnabled = true)
    static public class SecurityMajorConfig extends WebSecurityConfigurerAdapter {

        private final CustomOAuthUserService customOAuthUserService;

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .headers().frameOptions().disable()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/word/**", "/api/user/logout", "/api/user/push").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                    .anyRequest().permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/api/user/logout")
                    .invalidateHttpSession(true)
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                    .permitAll()
                    .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/api/user")
                    .userInfoEndpoint()
                    .userService(customOAuthUserService);
        }
    }
}
