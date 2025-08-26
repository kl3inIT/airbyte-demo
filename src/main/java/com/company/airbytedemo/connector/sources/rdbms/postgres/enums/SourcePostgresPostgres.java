package com.company.airbytedemo.connector.sources.rdbms.postgres.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum SourcePostgresPostgres implements EnumClass<String> {
    POSTGRES("postgres");

    private final String id;

    SourcePostgresPostgres(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static SourcePostgresPostgres fromId(String id) {
        for (SourcePostgresPostgres at : SourcePostgresPostgres.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}