package com.company.airbytedemo.dto;

import com.company.airbytedemo.dto.enums.DestinationS3Compression;
import com.company.airbytedemo.dto.enums.DestinationS3Flattening;
import com.company.airbytedemo.dto.enums.DestinationS3FormatType;
import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class DestinationS3CSVCommaSeparatedValuesDTO {

    private String compression;

    private String flattening;

    private String formatType;

    public DestinationS3Compression getCompression() {
        return compression == null ? null : DestinationS3Compression.fromId(compression);
    }

    public void setCompression(DestinationS3Compression compression) {
        this.compression = compression == null ? null : compression.getId();
    }

    public DestinationS3FormatType getFormatType() {
        return formatType == null ? null : DestinationS3FormatType.fromId(formatType);
    }

    public void setFormatType(DestinationS3FormatType formatType) {
        this.formatType = formatType == null ? null : formatType.getId();
    }

    public DestinationS3Flattening getFlattening() {
        return flattening == null ? null : DestinationS3Flattening.fromId(flattening);
    }

    public void setFlattening(DestinationS3Flattening flattening) {
        this.flattening = flattening == null ? null : flattening.getId();
    }


}