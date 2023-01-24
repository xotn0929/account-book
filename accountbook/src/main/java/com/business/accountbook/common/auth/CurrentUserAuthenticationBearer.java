package com.business.accountbook.common.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.business.accountbook.common.utils.JwtVerifyHandler;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CurrentUserAuthenticationBearer {
    public static Mono<Authentication> create(JwtVerifyHandler.VerificationResult verificationResult) {
        var claims = verificationResult.claims;
        var subject = claims.getSubject();
        List<String> roles = claims.get("role", List.class);
        var authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        String principalId = "";

        try {
            principalId = String.valueOf(subject);
        } catch (NumberFormatException ignore) { }

        if (principalId == "")
            return Mono.empty();

        var principal = new UserPrincipal(UUID.fromString(principalId), claims.getIssuer());

        return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(principal, null, authorities));
    }

}