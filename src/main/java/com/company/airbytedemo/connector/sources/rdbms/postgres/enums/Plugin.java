package com.company.airbytedemo.connector.sources.rdbms.postgres.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum Plugin implements EnumClass<String> {
    PGOUTPUT("pgoutput");

    private final String id;

    Plugin(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Plugin fromId(String id) {
        for (Plugin at : Plugin.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}