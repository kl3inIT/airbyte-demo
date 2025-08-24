package com.company.airbytedemo.dto;

import io.jmix.core.metamodel.annotation.JmixEntity;

import java.io.Serializable;

@JmixEntity(name = "DestinationDTO")
public class DestinationDTO implements Serializable {

    private String workplaceId;

    public String getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(String workplaceId) {
        this.workplaceId = workplaceId;
    }

}
