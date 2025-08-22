// DestinationDTODataType.java
package com.company.airbytedemo.converter;

import io.jmix.core.EntitySerialization;
import io.jmix.core.metamodel.annotation.DatatypeDef;
import io.jmix.core.metamodel.annotation.Ddl;
import io.jmix.core.metamodel.datatype.Datatype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Nullable;
import java.text.ParseException;
import java.util.Locale;

@DatatypeDef(
        id = "destinationDTO",                     // id duy nhất cho datatype này
        javaClass = DestinationDTODataType.class,  // class mà datatype này sẽ quản lý
        defaultForClass = false
)
@Ddl("jsonb") // PostgreSQL: jsonb; MySQL: JSON/LONGTEXT
public class DestinationDTODataType implements Datatype<DestinationDTODataType> {

    @Autowired
    @Lazy
    private EntitySerialization entitySerialization;

    @Override
    public String format(@Nullable Object value) {
        if (value == null) {
            return null;
        } else {
            return entitySerialization.objectToJson(value);
        }
    }

    @Override
    public String format(@Nullable Object value, Locale locale) {
        return format(value);
    }

    @Nullable
    @Override
    public DestinationDTODataType parse(@Nullable String value) throws ParseException {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return entitySerialization.objectFromJson(value, DestinationDTODataType.class);
        } catch (Exception e) {
            throw new ParseException("Cannot parse DestinationDTODataType: " + e.getMessage(), 0);
        }
    }

    @Nullable
    @Override
    public DestinationDTODataType parse(@Nullable String value, Locale locale) throws ParseException {
        return parse(value);
    }
}
