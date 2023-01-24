package com.business.accountbook.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.web.server.ServerWebExchange;

import com.business.accountbook.common.auth.CurrentUserAuthenticationBearer;

import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Predicate;

public class ServerHttpCookieAuthenticationConverter implements Function<ServerWebExchange, Mono<Authentication>> {

    private static final String BEARER = "Bearer ";
    private static final Predicate<String> matchBearerLength = authValue -> authValue.length() > BEARER.length();
    private static final Function<String, Mono<String>> isolateBearerValue = authValue -> Mono.justOrEmpty(authValue.substring(BEARER.length()));
    private final JwtVerifyHandler jwtVerifier;

    public ServerHttpCookieAuthenticationConverter(JwtVerifyHandler jwtVerifier) {
        this.jwtVerifier = jwtVerifier;
    }

    @Override
    public Mono<Authentication> apply(ServerWebExchange serverWebExchange) {
        return Mono.justOrEmpty(serverWebExchange)
                .flatMap(ServerHttpCookieAuthenticationConverter::extract)
                .flatMap(jwtVerifier::check)
                .flatMap(CurrentUserAuthenticationBearer::create);
    }

    public static Mono<String> extract(ServerWebExchange serverWebExchange) {
        var cookieSes = serverWebExchange.getRequest()
                .getCookies()
                .getFirst("X-Session-Id");

        return cookieSes != null
                ? Mono.justOrEmpty(cookieSes.getValue())
                : Mono.empty();
    }
}