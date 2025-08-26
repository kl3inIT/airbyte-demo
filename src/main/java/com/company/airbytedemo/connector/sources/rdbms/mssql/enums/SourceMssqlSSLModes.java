package com.company.airbytedemo.connector.sources.rdbms.mssql.enums;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum SourceMssqlSSLModes implements EnumClass<String> {

    UNENCRYPTED("unencrypted", "Unencrypted"),
    TRUST("trust", "Encrypted (trust server certificate)"),
    VERIFY("verify", "Encrypted (verify certificate)");


    private final String id;
    private final String displayName;

    SourceMssqlSSLModes(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Nullable
    public static SourceMssqlSSLModes fromId(String id) {
        if (id == null) return null;
        for (SourceMssqlSSLModes mode : values()) {
            if (mode.id.equalsIgnoreCase(id)) {
                return mode;
            }
        }
        return null;
    }
}