package com.company.airbytedemo.dto;

import com.airbyte.api.models.shared.DestinationS3S3BucketRegion;
import com.company.airbytedemo.dto.enums.DestinationS3BucketRegion;
import com.company.airbytedemo.dto.enums.DestinationS3OutputFormatType;
import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@JmixEntity(name="DestinationS3DTO")
public class DestinationS3DTO extends DestinationDTO {

    @JmixId
    @JmixGeneratedValue
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private String accessKeyId;
    private String fileNamePattern;
    private String format;
    private String roleArn;
    
    @NotNull
    private String s3BucketName;
    
    @NotNull
    private String s3BucketPath;
    
    private String s3BucketRegion;
    private String s3Endpoint;
    private String s3PathFormat;
    private String secretAccessKey;

    private S3FormatConfig s3FormatConfig;

    public S3FormatConfig getS3FormatConfig() {
        return s3FormatConfig;
    }

    public void setS3FormatConfig(S3FormatConfig s3FormatConfig) {
        this.s3FormatConfig = s3FormatConfig;
    }

    public DestinationS3OutputFormatType getFormat() {
        return format == null ? null : DestinationS3OutputFormatType.fromId(format);
    }

    public void setFormat(DestinationS3OutputFormatType format) {
        this.format = format == null ? null : format.getId();
    }

    public DestinationS3BucketRegion getS3BucketRegion() {
        return s3BucketRegion == null ? null : DestinationS3BucketRegion.fromId(s3BucketRegion);
    }

    public Optional<DestinationS3S3BucketRegion> getS3S3BucketRegion() {
        return DestinationS3S3BucketRegion.fromValue(s3BucketRegion);
    }

    public void setS3BucketRegion(DestinationS3BucketRegion s3BucketRegion) {
        this.s3BucketRegion = s3BucketRegion == null ? null : s3BucketRegion.getId();
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getFileNamePattern() {
        return fileNamePattern;
    }

    public void setFileNamePattern(String fileNamePattern) {
        this.fileNamePattern = fileNamePattern;
    }

    public String getRoleArn() {
        return roleArn;
    }

    public void setRoleArn(String roleArn) {
        this.roleArn = roleArn;
    }

    public String getS3BucketName() {
        return s3BucketName;
    }

    public void setS3BucketName(String s3BucketName) {
        this.s3BucketName = s3BucketName;
    }

    public String getS3BucketPath() {
        return s3BucketPath;
    }

    public void setS3BucketPath(String s3BucketPath) {
        this.s3BucketPath = s3BucketPath;
    }

    public String getS3Endpoint() {
        return s3Endpoint;
    }

    public void setS3Endpoint(String s3Endpoint) {
        this.s3Endpoint = s3Endpoint;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getS3PathFormat() {
        return s3PathFormat;
    }

    public void setS3PathFormat(String s3PathFormat) {
        this.s3PathFormat = s3PathFormat;
    }

    @InstanceName
    @DependsOnProperties({"s3BucketName"})
    public String getInstanceName(MetadataTools metadataTools) {
        return metadataTools.format(s3BucketName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DestinationS3DTO that = (DestinationS3DTO) o;
        return Objects.equals(this.getId(), that.getId()) &&
               Objects.equals(accessKeyId, that.accessKeyId) &&
               Objects.equals(fileNamePattern, that.fileNamePattern) &&
               Objects.equals(format, that.format) &&
               Objects.equals(roleArn, that.roleArn) &&
               Objects.equals(s3BucketName, that.s3BucketName) &&
               Objects.equals(s3BucketPath, that.s3BucketPath) &&
               Objects.equals(s3BucketRegion, that.s3BucketRegion) &&
               Objects.equals(s3Endpoint, that.s3Endpoint) &&
               Objects.equals(s3PathFormat, that.s3PathFormat) &&
               Objects.equals(secretAccessKey, that.secretAccessKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getId(), accessKeyId, fileNamePattern, format,
                          roleArn, s3BucketName, s3BucketPath, s3BucketRegion, 
                          s3Endpoint, s3PathFormat, secretAccessKey);
    }
}