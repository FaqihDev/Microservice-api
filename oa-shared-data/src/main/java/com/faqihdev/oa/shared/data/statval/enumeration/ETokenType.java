package com.faqihdev.oa.shared.dao.statval.enumeration;

import lombok.Getter;

@Getter
public enum ETokenType {

    BEARER("Bearer");

    private final String name;

    ETokenType(String name) {
        this.name = name;
    }
}
