package com.company.airbytedemo.dto;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;

@JmixEntity(name="DestinationPosgresDTO")
public class DestinationPosgresDTO extends DestinationDTO {

    @JmixId
    @JmixGeneratedValue
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DestinationPosgresDTO that = (DestinationPosgresDTO) o;
        return Objects.equals(this.getId(), that.getId()) &&
               Objects.equals(postgres, that.postgres) &&
               Objects.equals(userName, that.userName) &&
               Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getId(), postgres, userName, password);
    }
}
