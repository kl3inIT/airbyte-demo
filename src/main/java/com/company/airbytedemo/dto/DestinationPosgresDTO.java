package com.company.airbytedemo.dto;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Id;

import java.util.UUID;

@JmixEntity(name="DestinationPosgresDTO")
public class DestinationPosgresDTO extends DestinationDTO {
    @Id
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private String postgres;

    private String userName;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostgres() {
        return postgres;
    }

    public void setPostgres(String postgres) {
        this.postgres = postgres;
    }
}
