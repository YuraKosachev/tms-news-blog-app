package com.tms.gateway.core.enums;

public enum Role {
    PUBLISHER("ROLE_PUBLISHER"),
    ADMIN("ROLE_ADMIN"),
    READER("ROLE_READER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
