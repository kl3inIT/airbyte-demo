package com.company.airbytedemo.connect.destinations.s3.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DestinationS3FormatType implements EnumClass<String> {
    CSV("CSV");

    private final String id;

    DestinationS3FormatType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DestinationS3FormatType fromId(String id) {
        for (DestinationS3FormatType at : DestinationS3FormatType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}