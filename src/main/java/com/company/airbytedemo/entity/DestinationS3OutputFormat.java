package com.company.airbytedemo.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DestinationS3OutputFormat implements EnumClass<String> {

    CSV("csv"),
    JSON("json"),
    AVRO("avro"),
    PARQUET("parquet");

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