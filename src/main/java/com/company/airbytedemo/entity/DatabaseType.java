package com.company.airbytedemo.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DatabaseType implements EnumClass<String> {

    POSTGRES("postgres"),
    MYSQL("mysql"),
    MSSQL("mssql");

    private final String id;

    DatabaseType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DatabaseType fromId(String id) {
        for (DatabaseType at : DatabaseType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}