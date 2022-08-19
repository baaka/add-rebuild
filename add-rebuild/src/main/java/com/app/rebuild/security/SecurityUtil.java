package com.app.rebuild.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static UserPrincipal getCurrentUserPrincipal() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        return null;
    }

    public static String getPrincipalName() {
        return getCurrentUserPrincipal().getUsername();
    }

    public static long getCurrentUserId() {
        return getCurrentUserPrincipal().getUser().getId();
    }
}
