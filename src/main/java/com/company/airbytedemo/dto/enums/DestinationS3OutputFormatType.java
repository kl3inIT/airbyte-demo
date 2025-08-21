package com.company.airbytedemo.dto.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DestinationS3OutputFormatType implements EnumClass<String> {
    CSV("CSV: Comma-Separated Values"),
    JSON("JSON Lines: Newline-delimited JSON"),
    AVRO("Avro: Apache Avro"),
    PARQUET("Parquet: Columnar Storage");

    private final String id;

    DestinationS3OutputFormatType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DestinationS3OutputFormatType fromId(String id) {
        for (DestinationS3OutputFormatType at : DestinationS3OutputFormatType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}