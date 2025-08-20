package com.company.airbytedemo.connect.destinations.s3.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DestinationS3Flattening implements EnumClass<String> {

    NO_FLATTENING("No flattening"),
    ROOT_LEVEL_FLATTENING("Root level flattening");

    private final String id;

    DestinationS3Flattening(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DestinationS3Flattening fromId(String id) {
        for (DestinationS3Flattening at : DestinationS3Flattening.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}