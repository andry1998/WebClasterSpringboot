package com.example.test404.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.PERSONS_READ)),
    ADMIN(Set.of(Permission.PERSONS_READ, Permission.PERSONS_WRITE));

    private final Set<Permission> permissions;
    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
