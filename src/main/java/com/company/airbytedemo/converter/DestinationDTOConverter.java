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

    @Override
    public String convertToDatabaseColumn(DestinationDTO attribute) {
        if (attribute == null) return null;
        
        EntitySerialization entitySerialization = EntitySerializationUtils.getEntitySerialization();
        if (entitySerialization == null) {
            throw new IllegalStateException("EntitySerialization not available");
        }
        
        return entitySerialization.toJson(attribute, null, EntitySerializationOption.SERIALIZE_INSTANCE_NAME);
    }

    @Override
    public DestinationDTO convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) return null;
        
        EntitySerialization entitySerialization = EntitySerializationUtils.getEntitySerialization();
        if (entitySerialization == null) {
            throw new IllegalStateException("EntitySerialization not available");
        }
        
        return entitySerialization.entityFromJson(dbData, null, EntitySerializationOption.SERIALIZE_INSTANCE_NAME);
    }
}
