package com.company.airbytedemo.dto;

import com.company.airbytedemo.dto.enums.DestinationS3CompressionCodec;
import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class DestinationS3AvroApacheAvroDTO {

    private String codec;

    public DestinationS3CompressionCodec getCodec() {
        return codec == null ? null : DestinationS3CompressionCodec.fromId(codec);
    }

    public void setCodec(DestinationS3CompressionCodec codec) {
        this.codec = codec == null ? null : codec.getId();
    }

}