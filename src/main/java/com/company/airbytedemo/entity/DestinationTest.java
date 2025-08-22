package com.company.airbytedemo.entity;

import com.company.airbytedemo.converter.DestinationDTOConverter;
import com.company.airbytedemo.dto.DestinationDTO;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Ddl;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "DESTINATION_TEST")
@Entity
public class DestinationTest {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "WORKSPACE_ID", nullable = false)
    private String workspaceId;

    @Column(name = "DEFINITION_ID", nullable = false)
    private String definitionId;

    @Column(name = "DESTINATION_TYPE", nullable = false)
    private String destinationType;

    @Column(name = "RAW_CONFIG", nullable = false)
    @Lob
    private DestinationDTO rawConfig;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    public DestinationType getDestinationType() {
        return destinationType == null ? null : DestinationType.fromId(destinationType);
    }

    public void setDestinationType(DestinationType enums) {
        this.destinationType = enums == null ? null : enums.getId();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(String definitionId) {
        this.definitionId = definitionId;
    }

    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
    }

    public DestinationDTO getRawConfig() {
        return rawConfig;
    }

    public void setRawConfig(DestinationDTO rawConfig) {
        this.rawConfig = rawConfig;
    }
}