package com.company.airbytedemo.connector.destinations.aws.s3.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DestinationS3OutputFormat implements EnumClass<String> {
    CSV("CSV: Comma-Separated Values"),
    JSON("JSON Lines: Newline-delimited JSON"),
    AVRO("Avro: Apache Avro"),
    PARQUET("Parquet: Columnar Storage");

    private final String id;

    DestinationS3OutputFormat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DestinationS3OutputFormat fromId(String id) {
        for (DestinationS3OutputFormat at : DestinationS3OutputFormat.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}