package com.company.airbytedemo.connector.destinations.aws.s3.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DestinationS3CompressionCodec implements EnumClass<String> {
    NO_COMPRESSION("no compression"),
    DEFLATE("Deflate"),
    BZIP2("bzip2"),
    XZ("xz"),
    ZSTANDARD("zstandard"),
    SNAPPY("snappy");

    private final String id;

    DestinationS3CompressionCodec(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DestinationS3CompressionCodec fromId(String id) {
        for (DestinationS3CompressionCodec at : DestinationS3CompressionCodec.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}