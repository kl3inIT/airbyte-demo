package com.company.airbytedemo.connector.destinations.aws.s3.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DestinationS3OutputFormatType implements EnumClass<String> {
    CSV("csv"),
    JSON("json"),
    AVRO("arvo"),
    PARQUET("parquet");

    private final String id;

    DestinationS3OutputFormatType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DestinationS3OutputFormatType fromId(String id) {
        String normalized = id.trim();
        for (DestinationS3OutputFormatType at : DestinationS3OutputFormatType.values()) {
            if (at.getId().equalsIgnoreCase(normalized)) {
                return at;
            }
        }
        return null;
    }
}