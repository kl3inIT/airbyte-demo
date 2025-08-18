package com.company.airbytedemo.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum SourceType implements EnumClass<String> {

    API("api"),
    FILE("file"),
    DATABASE("database");

    private final String id;

    SourceType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static SourceType fromId(String id) {
        if (DatabaseType.fromId(id) != null) {
            return DATABASE;
        }

        if (id.isEmpty() || id == null) {
            return API;
        }

        for (SourceType at : SourceType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}