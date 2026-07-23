
package com.mirachi.util;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static String getCurrentUsername() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {

            return "SYSTEM";
        }

        return authentication.getName();
    }

    public static String getCurrentRole() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {

            return "SYSTEM";
        }

        Collection<? extends GrantedAuthority> authorities =
                authentication.getAuthorities();

        if (authorities == null || authorities.isEmpty()) {
            return "SYSTEM";
        }

        return authorities.iterator().next().getAuthority();
    }
}