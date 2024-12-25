package com.faqihdev.oa.shared.data.statval.enumeration;

import lombok.Getter;

@Getter
public enum EAgeCriteria {

    YOUNGER("YOUNGER"),OLDER("OLDER"),NONE("NONE");

    private String name;

    EAgeCriteria(String name) {
        this.name = name;
    }
}