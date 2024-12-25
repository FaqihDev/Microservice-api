package com.faqihdev.oa.shared.data.statval.enumeration;


import lombok.Getter;

@Getter
public enum EStatus {

    LAJANG("LAJANG"),DUDA("DUDA"),JANDA("JANDA"),PERAWAN("PERAWAN");


     private final String name;

    EStatus(String name) {
        this.name = name;
    }
}
