package com.github.riannegreiros.ExpressCleaning.config;

import com.github.riannegreiros.ExpressCleaning.core.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

        @Autowired
        private AuthenticationEntryPoint authenticationEntryPoint;

        @Autowired
        private AccessDeniedHandler accessDeniedHandler;

        @Value("${rememberMe.key}")
        private String rememberMeKey;

        @Value("${rememberMe.validitySeconds}")
        private int rememberMeValiditySeconds;

        @Bean
        @Order(1)
        public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
                http.securityMatchers(requestMatcherCustomizer -> requestMatcherCustomizer
                                .requestMatchers("/api/**", "/auth/**"))
                                .authorizeHttpRequests(authorizeRequestsCustomizer -> authorizeRequestsCustomizer
                                                .anyRequest()
                                                .permitAll())
                                .csrf(AbstractHttpConfigurer::disable)
                                .sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .exceptionHandling(exceptionHandlingCustomizer -> exceptionHandlingCustomizer
                                                .authenticationEntryPoint(authenticationEntryPoint)
                                                .accessDeniedHandler(accessDeniedHandler))
                                .cors(Customizer.withDefaults());

                return http.build();
        }

        @Bean
        @Order(2)
        public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
                http.securityMatchers(requestMatcherCustomizer -> requestMatcherCustomizer
                                .requestMatchers("/admin/**"))
                                .authorizeHttpRequests(authorizeRequestsCustomizer -> authorizeRequestsCustomizer
                                                .requestMatchers("/admin/reset-password/**").permitAll()
                                                .anyRequest()
                                                .hasAnyAuthority(UserType.ADMIN.name()))
                                .formLogin(formLoginCustomizer -> formLoginCustomizer
                                                .loginPage("/admin/login")
                                                .usernameParameter("email")
                                                .passwordParameter("password")
                                                .defaultSuccessUrl("/admin/services")
                                                .permitAll())
                                .logout(logoutCustomizer -> logoutCustomizer
                                                .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout", "GET"))
                                                .logoutSuccessUrl("/admin/login"))
                                .rememberMe(rememberMeCustomizer -> rememberMeCustomizer
                                                .rememberMeParameter("remember-me")
                                                .tokenValiditySeconds(rememberMeValiditySeconds)
                                                .key(rememberMeKey))
                                .exceptionHandling(exceptionHandlingCustomizer -> exceptionHandlingCustomizer
                                                .accessDeniedPage("/admin/login"));

                return http.build();
        }

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
                return web -> web.ignoring()
                                .requestMatchers("/webjars/**", "/img/**");
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
                return configuration.getAuthenticationManager();
        }
}
