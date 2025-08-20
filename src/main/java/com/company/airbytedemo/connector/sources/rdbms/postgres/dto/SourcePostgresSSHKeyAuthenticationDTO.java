package com.company.airbytedemo.connector.sources.rdbms.postgres.dto;

import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class SourcePostgresSSHKeyAuthenticationDTO {

    private String sshKey;

    private String tunnelHost;

    private Long tunnelPort;

    private String tunnelUser;

    public String getSshKey() {
        return sshKey;
    }

    public void setSshKey(String sshKey) {
        this.sshKey = sshKey;
    }

    public String getTunnelHost() {
        return tunnelHost;
    }

    public void setTunnelHost(String tunnelHost) {
        this.tunnelHost = tunnelHost;
    }

    public Long getTunnelPort() {
        return tunnelPort;
    }

    public void setTunnelPort(Long tunnelPort) {
        this.tunnelPort = tunnelPort;
    }

    public String getTunnelUser() {
        return tunnelUser;
    }

    public void setTunnelUser(String tunnelUser) {
        this.tunnelUser = tunnelUser;
    }
}