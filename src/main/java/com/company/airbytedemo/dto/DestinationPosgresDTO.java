package com.company.airbytedemo.dto;

import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class DestinationPosgresDTO extends DestinationDTO {

    private String postgres;

    public String getPostgres() {
        return postgres;
    }

    public void setPostgres(String postgres) {
        this.postgres = postgres;
    }
}
