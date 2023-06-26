package com.example.gpthomework.controller;

import com.example.gpthomework.security.UserPrincipal;
import com.example.gpthomework.security.UserPrincipalAuthToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;

public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockUser annotation) {

        var authorities = Arrays.stream(annotation.authorities())
                .map(SimpleGrantedAuthority::new)
                .toList();

        var principals = UserPrincipal.builder()
                .userId(annotation.userId())
                .email("fake@test.com")
                .authorities(authorities)
                .build();

        var context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(new UserPrincipalAuthToken(principals));

        return context;
    }
}
