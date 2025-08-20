package com.company.airbytedemo.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DestinationS3S3BucketRegion implements EnumClass<String> {

    US_EAST1("us-east-1");

    private final String id;

    DestinationS3S3BucketRegion(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DestinationS3S3BucketRegion fromId(String id) {
        for (DestinationS3S3BucketRegion at : DestinationS3S3BucketRegion.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}