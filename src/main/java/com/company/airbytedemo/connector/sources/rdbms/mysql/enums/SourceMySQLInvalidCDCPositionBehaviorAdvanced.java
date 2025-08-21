package com.company.airbytedemo.connector.sources.rdbms.mysql.enums;

import com.company.airbytedemo.connector.sources.rdbms.postgres.enums.SourcePostgresInvalidCDCPositionBehaviorAdvanced;
import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum SourceMySQLInvalidCDCPositionBehaviorAdvanced implements EnumClass<String> {
    FAIL_SYNC("Fail sync"),
    RE_SYNC_DATA("Re-sync data");

    private final String id;

    SourceMySQLInvalidCDCPositionBehaviorAdvanced(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static SourceMySQLInvalidCDCPositionBehaviorAdvanced fromId(String id) {
        for (SourceMySQLInvalidCDCPositionBehaviorAdvanced at : SourceMySQLInvalidCDCPositionBehaviorAdvanced.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}