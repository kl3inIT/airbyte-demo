package com.company.airbytedemo.utils;

import com.fasterxml.jackson.databind.JsonNode;

public class AirbyteUtils {

    public static String txt(JsonNode n, String k) {
        if (n == null) return null;
        JsonNode v = n.get(k);
        return (v == null || v.isNull()) ? null : v.asText();
    }

    public static Long longVal(JsonNode n, String k) {
        if (n == null) return null;
        JsonNode v = n.get(k);
        return (v == null || v.isNull()) ? null : v.asLong();
    }

    public static Boolean boolVal(JsonNode n, String k) {
        if (n == null) return null;
        JsonNode v = n.get(k);
        return (v == null || v.isNull()) ? null : v.asBoolean();
    }

    public static <T> T requireType(Object o, Class<T> type) {
        if (o == null) throw new IllegalArgumentException("Format config is required");
        if (!type.isInstance(o)) {
            throw new IllegalArgumentException("Format config mismatch. Expected " + type.getSimpleName()
                    + " but got " + o.getClass().getSimpleName());
        }
        return type.cast(o);
    }
}
