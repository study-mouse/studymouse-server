package com.studymouse.studymouseserver.config;

import com.studymouse.studymouseserver.security.CustomOAuthUserService;
import com.studymouse.studymouseserver.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class SecurityConfig {

    @RequiredArgsConstructor
    @EnableGlobalMethodSecurity(securedEnabled = true)
    static public class SecurityMajorConfig extends WebSecurityConfigurerAdapter {

        private final CustomOAuthUserService customOAuthUserService;

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http
                    .cors()
                    .and()
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

//        @Bean
//        public CorsConfigurationSource corsConfigurationSource() {
//            CorsConfiguration configuration = new CorsConfiguration();
//
//            configuration.addAllowedOrigin("*");
//            configuration.addAllowedHeader("*");
//            configuration.addAllowedMethod("*");
//            configuration.setAllowCredentials(true);
//
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            source.registerCorsConfiguration("/**", configuration);
//            return source;
//        }
    }
}
