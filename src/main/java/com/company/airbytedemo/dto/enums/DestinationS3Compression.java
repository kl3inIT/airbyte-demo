package com.company.airbytedemo.dto.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DestinationS3Compression implements EnumClass<String> {

    NO_COMPRESSION("No Compression"),
    GZIP("GZIP");

    private final String id;

    DestinationS3Compression(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DestinationS3Compression fromId(String id) {
        for (DestinationS3Compression at : DestinationS3Compression.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}