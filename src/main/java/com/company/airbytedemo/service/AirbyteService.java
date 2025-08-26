package com.company.airbytedemo.service;

import com.airbyte.api.Airbyte;
import com.airbyte.api.models.operations.*;
import com.airbyte.api.models.operations.CreateDeclarativeSourceDefinitionRequest;
import com.airbyte.api.models.shared.*;
import com.company.airbytedemo.connector.destinations.aws.s3.dto.DestinationS3CSVCommaSeparatedValuesDTO;
import com.company.airbytedemo.connector.destinations.aws.s3.dto.DestinationS3DTO;
import com.company.airbytedemo.connector.destinations.aws.s3.dto.DestinationS3JSONLinesNewlineDelimitedJSONDTO;
import com.company.airbytedemo.connector.destinations.aws.s3.S3FormatConfig;
import com.company.airbytedemo.connector.destinations.aws.s3.enums.DestinationS3CompressionTypeE;
import com.company.airbytedemo.connector.destinations.aws.s3.enums.DestinationS3FlatteningType;
import com.company.airbytedemo.connector.destinations.aws.s3.enums.DestinationS3OutputFormatType;
import com.company.airbytedemo.entity.DatabaseType;
import com.company.airbytedemo.utils.AirbyteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AirbyteService {

    private static final Logger log = LoggerFactory.getLogger(AirbyteService.class);

    private final Airbyte airbyte;

    @Value("${airbyte.workspace.id}")
    private String workspaceId;

    @Value("${airbyte.dest.s3.name:MinIO-dest}")
    private String s3DestName;

    @Value("${airbyte.dest.s3.access-key}")
    private String s3AccessKey;

    @Value("${airbyte.dest.s3.secret-key}")
    private String s3SecretKey;

    @Value("${airbyte.dest.s3.bucket}")
    private String s3Bucket;


    // API endpoint MinIO: http://host:9000  (KHÔNG phải 9001)
    @Value("${airbyte.dest.s3.endpoint}")
    private String s3Endpoint;

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
            if (type == null) {
                throw new IllegalArgumentException("Unsupported or null database type: " + dbTYpe);
            }
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

    public SourceResponse createFileSource(String name, String datasetName, String url, String format, String provider) {
        try {
            StorageProvider storageProvider = null;

            switch (provider.toLowerCase().trim()) {
                case "https" -> storageProvider = StorageProvider.of(HTTPSPublicWeb.builder().build());
                case "sftp" -> storageProvider = StorageProvider.of(SFTPSecureFileTransferProtocol.builder().build());
                default -> throw new IllegalArgumentException("Unsupported provider: " + provider);
            }

            SourceConfiguration config = SourceConfiguration.of(SourceFile.builder()
                    .datasetName(datasetName)
                    .format(FileFormat.fromValue(format))
                    .provider(storageProvider)
                    .url(url)
                    .build());

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
            log.error("Failed to create file source in Airbyte", e);
        }
        return null;
    }

    public DestinationResponse createDestination(String name, DestinationS3DTO s3Dto, S3FormatConfig format) {

        DestinationCreateRequest req = DestinationCreateRequest.builder()
                .name(name)
                .workspaceId(workspaceId)
                .configuration(DestinationConfiguration.of(DestinationS3.builder()
                        .accessKeyId(Optional.ofNullable(s3Dto.getAccessKeyId()))
                        .secretAccessKey(s3Dto.getSecretAccessKey())
                        .s3BucketName(s3Dto.getS3BucketName())
                        .s3BucketRegion(s3Dto.getS3S3BucketRegion())
                        .s3Endpoint(s3Dto.getS3Endpoint())
                        .format(buildOutputFormat(format, s3Dto.getFormat()))
                        .s3BucketPath(s3Dto.getS3BucketPath())
                        .fileNamePattern(Optional.ofNullable(s3Dto.getFileNamePattern()))
                        .roleArn(Optional.ofNullable(s3Dto.getRoleArn()))
                        .s3PathFormat(Optional.ofNullable(s3Dto.getS3PathFormat()))
                        .build()))
                .build();
        CreateDestinationResponse res = null;
        try {
            res = airbyte.destinations().createDestination()
                    .request(req)
                    .call();
        } catch (Exception e) {
            log.error("Failed to create destination in Airbyte", e);
            return null;
        }

        if (res.destinationResponse().isPresent()) {
            return res.destinationResponse().get();
        }
        return null;
    }

    private DestinationS3OutputFormat buildOutputFormat(S3FormatConfig s3FormatConfig, DestinationS3OutputFormatType formatType) {
        switch (formatType) {
            case CSV -> {
                DestinationS3CSVCommaSeparatedValuesDTO s3CsvDto = AirbyteUtils.requireType(s3FormatConfig, DestinationS3CSVCommaSeparatedValuesDTO.class);
                return DestinationS3OutputFormat.of(
                        DestinationS3CSVCommaSeparatedValues.builder()
                                .formatType(DestinationS3FormatType.CSV)
                                .compression(mapCsvCompression(s3CsvDto.getCompression()))
                                .flattening(mapCsvFlattening(s3CsvDto.getFlattening()))
                                .build());
            }
            case JSON -> {
                DestinationS3JSONLinesNewlineDelimitedJSONDTO s3JsonDto = AirbyteUtils.requireType(s3FormatConfig, DestinationS3JSONLinesNewlineDelimitedJSONDTO.class);
                return null;
            }
            case AVRO -> {
                return null;
            }
            case PARQUET -> {
                return null;
            }
        }
        return null;
    }

    private DestinationS3Compression mapCsvCompression(DestinationS3CompressionTypeE compressionType) {
        if (compressionType == null) return DestinationS3Compression.of(DestinationS3NoCompression.builder().build());
        return switch (compressionType) {
            case NO_COMPRESSION -> DestinationS3Compression.of(DestinationS3NoCompression.builder().build());
            case GZIP -> DestinationS3Compression.of(DestinationS3GZIP.builder().build());
        };
    }

    private DestinationS3Flattening mapCsvFlattening(DestinationS3FlatteningType flatteningType) {
        if (flatteningType == null) return DestinationS3Flattening.NO_FLATTENING;
        return switch (flatteningType) {
            case NO_FLATTENING -> DestinationS3Flattening.NO_FLATTENING;
            case ROOT_LEVEL_FLATTENING -> DestinationS3Flattening.ROOT_LEVEL_FLATTENING;
        };
    }
}



