package com.company.airbytedemo.connect.sourses.postgres.dto;

import com.airbyte.api.models.shared.SourcePostgresSSHTunnelMethod;
import io.jmix.core.metamodel.annotation.JmixEntity;

import java.util.Optional;

@JmixEntity
public class SourcePostgresDTO {

    private String database;

    private String host;

    private String jdbcUrlParams;

    private String password;

    private Long port;

//    private Optional<? extends SourcePostgresUpdateMethod> replicationMethod;

//    private Optional<? extends List<String>> schemas;

//    private SourcePostgresPostgres sourceType;

//    private Optional<? extends SourcePostgresSSLModes> sslMode;

    private Optional<? extends SourcePostgresSSHTunnelMethod> tunnelMethod;

    private String username;

}