// DestinationDTODataType.java
package com.company.airbytedemo.converter;

import com.company.airbytedemo.dto.DestinationDTO;
import com.company.airbytedemo.utils.EntitySerializationUtils;
import io.jmix.core.EntitySerialization;
import io.jmix.core.EntitySerializationOption;
import io.jmix.core.metamodel.annotation.DatatypeDef;
import io.jmix.core.metamodel.annotation.Ddl;
import io.jmix.core.metamodel.datatype.Datatype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Nullable;
import java.text.ParseException;
import java.util.Locale;

@DatatypeDef(
        id = "destinationDTO",
        javaClass = DestinationDTO.class,
        defaultForClass = true// ← QUAN TRỌNG: set false để không xung đột với JPA AttributeConverter
)
@Ddl("TEXT")
public class DestinationDTODataType implements Datatype<DestinationDTO> {

    @Override
    public String format(@Nullable Object value) {
        if (value == null) return null;
        
        EntitySerialization entitySerialization = EntitySerializationUtils.getEntitySerialization();
        if (entitySerialization == null) {
            return value.toString(); // Fallback
        }
        
        return entitySerialization.toJson(value, null, EntitySerializationOption.SERIALIZE_NULLS);
    }

    @Override
    public String format(@Nullable Object value, Locale locale) {
        return format(value);
    }

    @Nullable
    @Override
    public DestinationDTO parse(@Nullable String value) throws ParseException {
        if (value == null || value.isBlank()) return null;
        
        EntitySerialization entitySerialization = EntitySerializationUtils.getEntitySerialization();
        if (entitySerialization == null) {
            throw new ParseException("EntitySerialization not available", 0);
        }
        
        try {
            return entitySerialization.entityFromJson(value, null, EntitySerializationOption.SERIALIZE_NULLS);
        } catch (Exception e) {
            throw new ParseException("Cannot parse DestinationDTO: " + e.getMessage(), 0);
        }
    }

    @Nullable
    @Override
    public DestinationDTO parse(@Nullable String value, Locale locale) throws ParseException {
        return parse(value);
    }
}
