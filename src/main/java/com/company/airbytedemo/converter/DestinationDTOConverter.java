package com.company.airbytedemo.converter;

import com.company.airbytedemo.dto.DestinationDTO;
import io.jmix.core.EntitySerialization;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Converter(autoApply = true)
public class DestinationDTOConverter implements AttributeConverter<DestinationDTO, String> {

    @Autowired
    @Lazy
    private EntitySerialization entitySerialization;

    @Override
    public String convertToDatabaseColumn(DestinationDTO attribute) {
        return attribute == null ? null : entitySerialization.objectToJson(attribute);
    }

    @Override
    public DestinationDTO convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return null;
        } else {
            return entitySerialization.objectFromJson(dbData, DestinationDTO.class);
        }
    }
}

