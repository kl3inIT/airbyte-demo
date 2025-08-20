package com.company.airbytedemo.entity;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.validation.constraints.NotNull;

@JmixEntity
public class DestinationS3DTO {

    private String accessKeyId;

    private String secretAccessKey;

    @NotNull
    private String s3BucketName;

    private String s3BucketPath;

    @NotNull
    private String s3BucketRegion;

    private String s3OutputFormat;

    private String roleArn;

    private String s3Endpoint;

    private String s3PathFormat;

    private String fileNamePattern;

    public String getFileNamePattern() {
        return fileNamePattern;
    }

    public void setFileNamePattern(String fileNamePattern) {
        this.fileNamePattern = fileNamePattern;
    }

    public String getS3PathFormat() {
        return s3PathFormat;
    }

    public void setS3PathFormat(String s3PathFormat) {
        this.s3PathFormat = s3PathFormat;
    }

    public String getS3Endpoint() {
        return s3Endpoint;
    }

    public void setS3Endpoint(String s3Endpoint) {
        this.s3Endpoint = s3Endpoint;
    }

    public String getRoleArn() {
        return roleArn;
    }

    public void setRoleArn(String roleArn) {
        this.roleArn = roleArn;
    }

    public DestinationS3OutputFormat getS3OutputFormat() {
        return s3OutputFormat == null ? null : DestinationS3OutputFormat.fromId(s3OutputFormat);
    }

    public void setS3OutputFormat(DestinationS3OutputFormat s3OutputFormat) {
        this.s3OutputFormat = s3OutputFormat == null ? null : s3OutputFormat.getId();
    }

    public String getS3BucketName() {
        return s3BucketName;
    }

    public void setS3BucketName(String s3BucketName) {
        this.s3BucketName = s3BucketName;
    }


    public DestinationS3S3BucketRegion getS3BucketRegion() {
        return s3BucketRegion == null ? null : DestinationS3S3BucketRegion.fromId(s3BucketRegion);
    }

    public void setS3BucketRegion(DestinationS3S3BucketRegion s3BucketRegion) {
        this.s3BucketRegion = s3BucketRegion == null ? null : s3BucketRegion.getId();
    }

    public String getS3BucketPath() {
        return s3BucketPath;
    }

    public void setS3BucketPath(String s3BucketPath) {
        this.s3BucketPath = s3BucketPath;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }


}