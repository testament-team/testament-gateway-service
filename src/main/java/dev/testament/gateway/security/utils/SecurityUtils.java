package dev.testament.gateway.security.utils;

import org.springframework.security.core.Authentication;

public final class SecurityUtils {

    public static String getUsername(Authentication authentication) {
        return authentication.getPrincipal().toString();
    }

}
