package com.company.airbytedemo.connector.sources.rdbms.postgres.dto;

import com.company.airbytedemo.connector.sources.rdbms.postgres.enums.SourcePostgresPostgres;
import com.company.airbytedemo.connector.sources.rdbms.postgres.enums.SourcePostgresSSHTunnelMethod;
import com.company.airbytedemo.connector.sources.rdbms.postgres.enums.SourcePostgresSSLModes;
import com.company.airbytedemo.connector.sources.rdbms.postgres.enums.SourcePostgresUpdateMethod;
import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class SourcePostgresDTO {

    // thieu name sourse nhi

    private String host;

    private Long port;

    private String database;

    private String schemas;

    private String username;

    private String password;

    private String sslMode;

    private String tunnelMethod;

    private String replicationMethod;

    private String sourceType;

    private String jdbcUrlParams;

    public SourcePostgresUpdateMethod getReplicationMethod() {
        return replicationMethod == null ? null : SourcePostgresUpdateMethod.fromId(replicationMethod);
    }

    public void setReplicationMethod(SourcePostgresUpdateMethod replicationMethod) {
        this.replicationMethod = replicationMethod == null ? null : replicationMethod.getId();
    }

    public SourcePostgresPostgres getSourceType() {
        return sourceType == null ? null : SourcePostgresPostgres.fromId(sourceType);
    }

    public void setSourceType(SourcePostgresPostgres sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.getId();
    }

    public SourcePostgresSSLModes getSslMode() {
        return sslMode == null ? null : SourcePostgresSSLModes.fromId(sslMode);
    }

    public void setSslMode(SourcePostgresSSLModes sslMode) {
        this.sslMode = sslMode == null ? null : sslMode.getId();
    }

    public SourcePostgresSSHTunnelMethod getTunnelMethod() {
        return tunnelMethod == null ? null : SourcePostgresSSHTunnelMethod.fromId(tunnelMethod);
    }

    public void setTunnelMethod(SourcePostgresSSHTunnelMethod tunnelMethod) {
        this.tunnelMethod = tunnelMethod == null ? null : tunnelMethod.getId();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getSchemas() {
        return schemas;
    }

    public void setSchemas(String schemas) {
        this.schemas = schemas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcUrlParams() {
        return jdbcUrlParams;
    }

    public void setJdbcUrlParams(String jdbcUrlParams) {
        this.jdbcUrlParams = jdbcUrlParams;
    }
}