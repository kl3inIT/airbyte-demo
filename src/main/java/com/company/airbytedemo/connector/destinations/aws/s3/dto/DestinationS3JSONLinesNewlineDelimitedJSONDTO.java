package com.company.airbytedemo.connector.destinations.aws.s3.dto;

import com.company.airbytedemo.connector.destinations.aws.s3.enums.DestinationS3FormatType;
import com.company.airbytedemo.connector.destinations.aws.s3.enums.DestinationS3Compression;
import com.company.airbytedemo.connector.destinations.aws.s3.enums.DestinationS3Flattening;
import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class DestinationS3JSONLinesNewlineDelimitedJSONDTO {

    private String compression;

    private String flattening;

    private String formatType;

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

    public DestinationS3Compression getCompression() {
        return compression == null ? null : DestinationS3Compression.fromId(compression);
    }

    public void setCompression(DestinationS3Compression compression) {
        this.compression = compression == null ? null : compression.getId();
    }

}