package com.company.airbytedemo.dto;

import com.company.airbytedemo.dto.enums.DestinationS3CompressionTypeE;
import com.company.airbytedemo.dto.enums.DestinationS3FlatteningType;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;

import java.util.UUID;

@JmixEntity
public class DestinationS3CSVCommaSeparatedValuesDTO extends S3FormatConfig {

    @JmixId
    @JmixGeneratedValue
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private String compression;

    private String flattening;

    public DestinationS3CompressionTypeE getCompression() {
        return compression == null ? null : DestinationS3CompressionTypeE.fromId(compression);
    }

    public void setCompression(DestinationS3CompressionTypeE compression) {
        this.compression = compression == null ? null : compression.getId();
    }

    public DestinationS3FlatteningType getFlattening() {
        return flattening == null ? null : DestinationS3FlatteningType.fromId(flattening);
    }

    public void setFlattening(DestinationS3FlatteningType flattening) {
        this.flattening = flattening == null ? null : flattening.getId();
    }


}