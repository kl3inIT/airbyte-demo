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
}
