package com.company.airbytedemo.service;

import com.airbyte.api.Airbyte;
import com.airbyte.api.models.operations.*;
import com.airbyte.api.models.operations.CreateDeclarativeSourceDefinitionRequest;
import com.airbyte.api.models.shared.*;
import com.company.airbytedemo.entity.DatabaseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AirbyteService {

    private static final Logger log = LoggerFactory.getLogger(AirbyteService.class);

    private final Airbyte airbyte;

    @Value("${airbyte.workspace.id}")
    private String workspaceId;

    public AirbyteService(Airbyte airbyte) {
        this.airbyte = airbyte;
    }

    public List<SourceResponse> getSources() {
        try {
            ListSourcesRequest req = ListSourcesRequest.builder()
                    .workspaceIds(List.of(workspaceId))
                    .build();

            ListSourcesResponse res = airbyte.sources()
                    .listSources()
                    .request(req)
                    .call();

            if (res.sourcesResponse().isPresent()) {
                return res.sourcesResponse().get().data();
            }
            log.warn("Airbyte returned empty SourcesResponse");
            return Collections.emptyList();

        } catch (Exception e) {
            log.error("Failed to fetch sources from Airbyte", e);
            return Collections.emptyList();
        }
    }

    public DeclarativeSourceDefinitionResponse createDeclarativeSourceDefinition(String name, String manifest) {
        try {
            CreateDeclarativeSourceDefinitionRequest req = CreateDeclarativeSourceDefinitionRequest.builder()
                    .createDeclarativeSourceDefinitionRequest(com.airbyte.api.models.shared.CreateDeclarativeSourceDefinitionRequest.builder()
                            .manifest(manifest)
                            .name(name)
                            .build())
                    .workspaceId(workspaceId)
                    .build();

            CreateDeclarativeSourceDefinitionResponse res = airbyte.declarativeSourceDefinitions().createDeclarativeSourceDefinition()
                    .request(req)
                    .call();

            if (res.declarativeSourceDefinitionResponse().isPresent()) {
                return res.declarativeSourceDefinitionResponse().get();
            }
        } catch (Exception e) {
            log.error("Failed to create declarative source definition in Airbyte", e);
            return null;
        }
        return null;
    }

    public SourceResponse createDatabaseSource(String name, String dbTYpe, String database, String host, Long port, String username, String password) {
        try {
            SourceConfiguration config;
            DatabaseType type = DatabaseType.fromId(dbTYpe);
            switch (type) {
                case POSTGRES -> config = SourceConfiguration.of(SourcePostgres.builder()
                        .database(database)
                        .host(host)
                        .port(port)
                        .replicationMethod(SourcePostgresUpdateMethod.of(
                                DetectChangesWithXminSystemColumn.builder().build()
                        ))
                        .sslMode(SourcePostgresSSLModes.of(
                                SourcePostgresDisable.builder().build()
                        ))
                        .tunnelMethod(SourcePostgresSSHTunnelMethod.of(
                                SourcePostgresNoTunnel.builder().build()
                        ))
                        .username(username)
                        .password(password)
                        .build());

                case MYSQL -> config = SourceConfiguration.of(SourceMysql.builder()
                        .database(database)
                        .host(host)
                        .port(port)

                        .username(username)
                        .password(password)
                        .build());

                case MSSQL -> config = SourceConfiguration.of(SourceMssql.builder()
                        .database(database)
                        .host(host)
                        .port(port)
                        .replicationMethod(UpdateMethod.of(
                                ReadChangesUsingChangeDataCaptureCDC.builder().build()
                        ))
                        .sslMethod(SourceMssqlSSLMethod.of(
                                SourceMssqlUnencrypted.builder().build()
                        ))
                        .tunnelMethod(SourceMssqlSSHTunnelMethod.of(
                                SourceMssqlNoTunnel.builder().build()
                        ))
                        .username(username)
                        .password(password)
                        .build());

                default -> throw new IllegalArgumentException("Unsupported database type: " + type);
            }

            SourceCreateRequest req = SourceCreateRequest.builder()
                    .configuration(config)
                    .name(name)
                    .workspaceId(workspaceId)
                    .build();

            CreateSourceResponse res = airbyte.sources().createSource()
                    .request(req)
                    .call();

            if (res.sourceResponse().isPresent()) {
                return res.sourceResponse().get();
            }
        } catch (Exception e) {
            log.error("Failed to create source in Airbyte", e);
        }
        return null;
    }


}

