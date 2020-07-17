package com.studymouse.studymouseserver.security;

import com.studymouse.studymouseserver.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
                    .antMatchers("/api/word/**").hasRole(Role.USER.name())
                    .anyRequest().permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/api/user/sociallogout")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/api/user")
                    .permitAll()
                    .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/api/user")
                    .failureUrl("/api/user/loginFail")
                    .userInfoEndpoint()
                    .userService(customOAuthUserService);
        }
    }
}
