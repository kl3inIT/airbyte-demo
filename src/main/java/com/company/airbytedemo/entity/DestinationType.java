package com.company.airbytedemo.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DestinationType implements EnumClass<String> {

    S3("s3");

    private final String id;

    DestinationType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DestinationType fromId(String id) {
        for (DestinationType at : DestinationType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}