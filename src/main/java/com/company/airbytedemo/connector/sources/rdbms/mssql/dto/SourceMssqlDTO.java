package com.company.airbytedemo.connector.sources.rdbms.mssql.dto;

import com.company.airbytedemo.connector.sources.rdbms.common.enums.SourceSSHTunnelMethod;
import com.company.airbytedemo.connector.sources.rdbms.mssql.enums.SourceMssqlSSLModes;
import com.company.airbytedemo.connector.sources.rdbms.mssql.enums.SourceMssqlUpdateMethod;
import com.company.airbytedemo.entity.DatabaseType;
import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class SourceMssqlDTO {

    private String host;
    private Long port;
    private String database;
    private String username;
    private String password;
    private String sslMode;
    private String replicationMethod;
    private String schemas;
    private String sourceType;       // ví dụ: "mssql"; "postgre"
    private String tunnelMethod;
    private String jdbcUrlParams;

    public DatabaseType getSourceType() {
        return sourceType == null ? null : DatabaseType.fromId(sourceType);
    }

    public void setSourceType(DatabaseType sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.getId();
    }

    public SourceMssqlSSLModes getSslMode() {
        return sslMode == null ? null : SourceMssqlSSLModes.fromId(sslMode);
    }

    public void setSslMode(SourceMssqlSSLModes sslMode) {
        this.sslMode = sslMode == null ? null : sslMode.getId();
    }

    public SourceMssqlUpdateMethod getReplicationMethod() {
        return replicationMethod == null ? null : SourceMssqlUpdateMethod.fromId(replicationMethod);
    }

    public void setReplicationMethod(SourceMssqlUpdateMethod replicationMethod) {
        this.replicationMethod = replicationMethod == null ? null : replicationMethod.getId();
    }

// 2 cai no y het nhau len la call enum tu postgres
    public SourceSSHTunnelMethod getTunnelMethod() {
        return tunnelMethod == null ? null : SourceSSHTunnelMethod.fromId(tunnelMethod);
    }

    public void setTunnelMethod(SourceSSHTunnelMethod tunnelMethod) {
        this.tunnelMethod = tunnelMethod == null ? null : tunnelMethod.getId();
    }



    // Getters & Setters
    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public Long getPort() { return port; }
    public void setPort(Long port) { this.port = port; }

    public String getDatabase() { return database; }
    public void setDatabase(String database) { this.database = database; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }


    public String getJdbcUrlParams() { return jdbcUrlParams; }
    public void setJdbcUrlParams(String jdbcUrlParams) { this.jdbcUrlParams = jdbcUrlParams; }

    public String getSchemas() {
        return schemas;
    }

    public void setSchemas(String schemas) {
        this.schemas = schemas;
    }
}
