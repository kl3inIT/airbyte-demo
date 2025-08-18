package com.company.airbytedemo.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue("FILE")
@JmixEntity
@Entity
public class FileSource extends DataSource {

    @Column(name = "DATASET_NAME")
    private String datasetName;

    @Column(name = "FORMAT")
    private String format;

    @Column(name = "PROVIDER")
    private String provider;

    @Column(name = "URL")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public FileFormat getFormat() {
        return format == null ? null : FileFormat.fromId(format);
    }

    public void setFormat(FileFormat format) {
        this.format = format == null ? null : format.getId();
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

}