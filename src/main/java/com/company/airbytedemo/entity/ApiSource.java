package com.company.airbytedemo.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@JmixEntity
@Entity
public class ApiSource extends DataSource {
    @Column(name = "DECLARATIVE_MANIFEST_JSON")
    @Lob
    private String declarativeManifestJson;

    @Column(name = "DECLARATIVE_DEFINITION_ID")
    @Lob
    private String declarativeDefinitionId;

    @Column(name = "BASE_URL")
    @Lob
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getDeclarativeDefinitionId() {
        return declarativeDefinitionId;
    }

    public void setDeclarativeDefinitionId(String declarativeDefinitionId) {
        this.declarativeDefinitionId = declarativeDefinitionId;
    }

    public String getDeclarativeManifestJson() {
        return declarativeManifestJson;
    }

    public void setDeclarativeManifestJson(String declarativeManifestJson) {
        this.declarativeManifestJson = declarativeManifestJson;
    }

}