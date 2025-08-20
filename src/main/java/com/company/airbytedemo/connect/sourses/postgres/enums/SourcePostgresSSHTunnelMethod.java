package com.company.airbytedemo.connect.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum SourcePostgresSSHTunnelMethod implements EnumClass<String> {
    NO_TUNNEL("NO_TUNNEL"),

    ;

    private final String id;

    SourcePostgresSSHTunnelMethod(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static SourcePostgresSSHTunnelMethod fromId(String id) {
        for (SourcePostgresSSHTunnelMethod at : SourcePostgresSSHTunnelMethod.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}