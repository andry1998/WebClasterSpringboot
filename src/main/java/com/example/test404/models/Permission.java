package com.example.test404.models;

public enum Permission {
    PERSONS_READ("persons:read"),
    PERSONS_WRITE("persons:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
