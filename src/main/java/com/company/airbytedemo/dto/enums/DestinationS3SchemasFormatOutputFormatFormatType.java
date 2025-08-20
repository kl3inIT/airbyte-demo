package com.company.airbytedemo.dto.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DestinationS3SchemasFormatOutputFormatFormatType implements EnumClass<String> {
    PARQUET("Parquet");
    ;

    private final String id;

    DestinationS3SchemasFormatOutputFormatFormatType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DestinationS3SchemasFormatOutputFormatFormatType fromId(String id) {
        for (DestinationS3SchemasFormatOutputFormatFormatType at : DestinationS3SchemasFormatOutputFormatFormatType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}