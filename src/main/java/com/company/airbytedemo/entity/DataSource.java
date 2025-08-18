package com.company.airbytedemo.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "DATA_SOURCE")
@Entity
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("DB_SOURCE")
public class DataSource {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "AIRBYTE_SOURCE_ID", length = 100)
    private String airbyteSourceId;

    @Column(name = "RAW_CONFIGURATION")
    @Lob
    private String rawConfiguration;

    public String getRawConfiguration() {
        return rawConfiguration;
    }

    public void setRawConfiguration(String configuration) {
        this.rawConfiguration = configuration;
    }

    public String getAirbyteSourceId() {
        return airbyteSourceId;
    }

    public void setAirbyteSourceId(String airbyteSourceId) {
        this.airbyteSourceId = airbyteSourceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}