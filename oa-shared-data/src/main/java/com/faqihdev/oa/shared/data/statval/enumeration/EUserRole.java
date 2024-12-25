package com.faqihdev.oa.shared.data.statval.enumeration;

import lombok.Getter;

@Getter
public enum EUserRole {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_PRINCIPLE("ROLE_PRINCIPLE");
    private final String name;

    EUserRole(String name) {
        this.name = name;
    }

}