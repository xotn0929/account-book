package com.business.accountbook.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import com.business.accountbook.common.utils.JwtVerifyHandler;
import com.business.accountbook.common.utils.ServerHttpBearerAuthenticationConverter;
import com.business.accountbook.common.utils.ServerHttpCookieAuthenticationConverter;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig{
    private final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${app.public_routes}")
    private String[] publicRoutes;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, AuthenticationManager authManager) {
        return http
                .authorizeExchange()
                    .pathMatchers(HttpMethod.OPTIONS)
                        .permitAll()
                    .pathMatchers(publicRoutes)
                        .permitAll()
                    .pathMatchers( "/favicon.ico")
                        .permitAll()
                    .anyExchange()
                        .authenticated()
                    .and()
                .csrf()
                    .disable()
                .httpBasic()
                    .disable()
                .formLogin()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint((swe, e) -> {
                        logger.info("[1] Authentication error: Unauthorized[401]: " + e.getMessage());

                        return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));
                    })
                    .accessDeniedHandler((swe, e) -> {
                        logger.info("[2] Authentication error: Access Denied[401]: " + e.getMessage());

                        return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN));
                    })
                .and()
                .addFilterAt(bearerAuthenticationFilter(authManager), SecurityWebFiltersOrder.AUTHENTICATION)
                .addFilterAt(cookieAuthenticationFilter(authManager), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    AuthenticationWebFilter bearerAuthenticationFilter(AuthenticationManager authManager) {
        AuthenticationWebFilter bearerAuthenticationFilter = new AuthenticationWebFilter(authManager);
        //bearerAuthenticationFilter.setAuthenticationConverter(new ServerHttpBearerAuthenticationConverter(new JwtVerifyHandler(jwtSecret)));
        bearerAuthenticationFilter.setAuthenticationConverter(new ServerHttpBearerAuthenticationConverter(new JwtVerifyHandler(jwtSecret)));
        bearerAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));

        return bearerAuthenticationFilter;
    }

    AuthenticationWebFilter cookieAuthenticationFilter(AuthenticationManager authManager) {
        AuthenticationWebFilter cookieAuthenticationFilter = new AuthenticationWebFilter(authManager);
        cookieAuthenticationFilter.setAuthenticationConverter(new ServerHttpCookieAuthenticationConverter(new JwtVerifyHandler(jwtSecret)));
        cookieAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));

        return cookieAuthenticationFilter;
    }
}
