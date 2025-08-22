package com.company.airbytedemo.converter;

import com.company.airbytedemo.dto.DestinationDTO;
import com.company.airbytedemo.dto.DestinationS3DTO;
import com.company.airbytedemo.utils.EntitySerializationUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import io.jmix.core.EntitySerialization;
import io.jmix.core.EntitySerializationOption;
import io.jmix.core.Metadata;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DestinationDTOConverter implements AttributeConverter<DestinationDTO, String> {

    EntitySerialization entitySerialization = EntitySerializationUtils.getEntitySerialization();
    Metadata metadata = EntitySerializationUtils.getMetaData();

    @Override
    public String convertToDatabaseColumn(DestinationDTO attribute) {
        if (attribute == null) return null;
        return entitySerialization.objectToJson(attribute, EntitySerializationOption.SERIALIZE_INSTANCE_NAME);
    }

    @Override
    public DestinationDTO convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) return null;
        return entitySerialization.entityFromJson(dbData, metadata.getClass(DestinationS3DTO.class), EntitySerializationOption.SERIALIZE_INSTANCE_NAME);
    }
}
