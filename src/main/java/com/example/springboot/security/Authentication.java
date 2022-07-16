package com.example.springboot.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@AllArgsConstructor
@Value
@Builder
public class Authentication {
    private final long id;
    private final List<String> roles;

    public boolean hasRole(final String role) {
        return roles.contains(role);
    }
}
