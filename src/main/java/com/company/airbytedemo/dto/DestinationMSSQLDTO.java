package com.company.airbytedemo.dto;

import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class DestinationMSSQLDTO extends DestinationDTO {

    private String mssql;

    public String getMssql() {
        return mssql;
    }

    public void setMssql(String mssql) {
        this.mssql = mssql;
    }
}
