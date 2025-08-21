package com.company.airbytedemo.utils;

import com.company.airbytedemo.dto.*;
import com.company.airbytedemo.dto.enums.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jmix.core.DataManager;

public final class DestinationS3Parser {

    public record Parsed(
            DestinationS3DTO root,
            DestinationS3CSVCommaSeparatedValuesDTO csv,
            DestinationS3JSONLinesNewlineDelimitedJSONDTO json,
            DestinationS3AvroApacheAvroDTO avro,
            DestinationS3ParquetColumnarStorageDTO parquet
    ) {}

    public static Parsed parse(String rawConfig, ObjectMapper mapper, DataManager dm) throws Exception {
        DestinationS3DTO root = dm.create(DestinationS3DTO.class);
        DestinationS3CSVCommaSeparatedValuesDTO csv = null;
        DestinationS3JSONLinesNewlineDelimitedJSONDTO json = null;
        DestinationS3AvroApacheAvroDTO avro = null;
        DestinationS3ParquetColumnarStorageDTO parquet = null;

        if (rawConfig == null || rawConfig.isBlank()) {
            // có thể set default format nếu muốn
            return new Parsed(root, null, null, null, null);
        }

        JsonNode rootNode = mapper.readTree(rawConfig);
        // Cho phép lưu full payload {name, workspaceId, definitionId, configuration:{...}}
        JsonNode cfg = rootNode.has("configuration") ? rootNode.get("configuration") : rootNode;

        // --- Common S3 fields ---
        root.setAccessKeyId(AirbyteUtils.txt(cfg, "access_key_id"));
        root.setSecretAccessKey(AirbyteUtils.txt(cfg, "secret_access_key"));
        root.setS3BucketName(AirbyteUtils.txt(cfg, "s3_bucket_name"));
        root.setS3BucketPath(AirbyteUtils.txt(cfg, "s3_bucket_path"));
        root.setS3Endpoint(AirbyteUtils.txt(cfg, "s3_endpoint"));
        root.setS3PathFormat(AirbyteUtils.txt(cfg, "s3_path_format"));
        root.setFileNamePattern(AirbyteUtils.txt(cfg, "file_name_pattern"));

        // destination_type (nếu bạn muốn default)
        String destType = AirbyteUtils.txt(cfg, "destination_type");
        root.setDestinationType(destType != null ? destType : "s3");

        // Region (map snake-case -> enum id)
        String regionId = AirbyteUtils.txt(cfg, "s3_bucket_region");
        if (regionId != null) {
            root.setS3BucketRegion(DestinationS3S3BucketRegion.fromId(regionId));
        }

        // --- Format ---
        JsonNode fmt = cfg.path("format");
        String formatTypeId = AirbyteUtils.txt(fmt, "format_type"); // "CSV" | "JSONL" | "AVRO" | "PARQUET"
        if (formatTypeId != null) {
            // DestinationS3OutputFormat là enum của BẠN (không phải Speakeasy)
            // Nếu enum của bạn dùng "JSON" thay cho "JSONL", map nhẹ:
            String outFmt = "JSONL".equalsIgnoreCase(formatTypeId) ? "JSON" : formatTypeId;
            root.setFormat(DestinationS3OutputFormat.fromId(outFmt));
        }

        DestinationS3OutputFormat out = root.getFormat();

        // --- CSV ---
        if (out == DestinationS3OutputFormat.CSV) {
            csv = dm.create(DestinationS3CSVCommaSeparatedValuesDTO.class);
            csv.setFlattening(DestinationS3Flattening.fromId(AirbyteUtils.txt(fmt, "flattening")));
            JsonNode comp = fmt.path("compression");
            csv.setCompression(DestinationS3Compression.fromId(AirbyteUtils.txt(comp, "compression_type")));
            csv.setFormatType(DestinationS3FormatType.fromId("CSV"));
        }

        // --- JSON / JSONL ---
        if (out == DestinationS3OutputFormat.JSON || "JSONL".equalsIgnoreCase(formatTypeId)) {
            json = dm.create(DestinationS3JSONLinesNewlineDelimitedJSONDTO.class);
            json.setFlattening(DestinationS3Flattening.fromId(AirbyteUtils.txt(fmt, "flattening")));
            JsonNode comp = fmt.path("compression");
            json.setCompression(DestinationS3Compression.fromId(AirbyteUtils.txt(comp, "compression_type")));
            json.setFormatType(DestinationS3FormatType.fromId("JSONL"));
        }

        // --- AVRO ---
        if (out == DestinationS3OutputFormat.AVRO) {
            avro = dm.create(DestinationS3AvroApacheAvroDTO.class);
            avro.setCodec(DestinationS3CompressionCodec.fromId(AirbyteUtils.txt(fmt, "codec")));
        }

        // --- PARQUET ---
        if (out == DestinationS3OutputFormat.PARQUET) {
            parquet = dm.create(DestinationS3ParquetColumnarStorageDTO.class);
            parquet.setCompressionCodec(DestinationS3SchemasCompressionCodec.fromId(AirbyteUtils.txt(fmt, "compression_codec")));
            parquet.setBlockSizeMb(AirbyteUtils.longVal(fmt, "block_size_mb"));
            parquet.setPageSizeKb(AirbyteUtils.longVal(fmt, "page_size_kb"));
            parquet.setDictionaryEncoding(AirbyteUtils.boolVal(fmt, "dictionary_encoding"));
            parquet.setDictionaryPageSizeKb(AirbyteUtils.longVal(fmt, "dictionary_page_size_kb"));
            parquet.setMaxPaddingSizeMb(AirbyteUtils.longVal(fmt, "max_padding_size_mb"));
            parquet.setFormatType(AirbyteUtils.txt(fmt, "format_type")); // thường "PARQUET"
        }

        return new Parsed(root, csv, json, avro, parquet);
    }

    private DestinationS3Parser() {}
}
