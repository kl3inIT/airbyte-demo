package com.company.airbytedemo.connector.destinations.aws.s3.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DestinationS3SchemasCompressionCodec implements EnumClass<String> {
    UNCOMPRESSED("UNCOMPRESSED"),
    SNAPPY("SNAPPY"),
    GZIP("GZIP"),
    LZO("LZO"),
    BROTLI("BROTLI"),
    LZ4("LZ4"),
    ZSTD("ZSTD");

    private final String id;

    DestinationS3SchemasCompressionCodec(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DestinationS3SchemasCompressionCodec fromId(String id) {
        for (DestinationS3SchemasCompressionCodec at : DestinationS3SchemasCompressionCodec.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}