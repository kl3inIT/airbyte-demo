package com.company.airbytedemo.connector.sources.rdbms.mssql.dto;

import com.company.airbytedemo.connector.sources.rdbms.postgres.enums.SourcePostgresInvalidCDCPositionBehaviorAdvanced;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;

import java.util.UUID;

@JmixEntity
public class SqlServerCdcDTO {

    private Long initialWaitingSeconds;

    private String invalidCdcCursorPositionBehavior;

    private Long queueSize;

    private Long initialLoadTimeoutHours;


    public SourcePostgresInvalidCDCPositionBehaviorAdvanced getInvalidCdcCursorPositionBehavior() {
        return invalidCdcCursorPositionBehavior == null ? null : SourcePostgresInvalidCDCPositionBehaviorAdvanced.fromId(invalidCdcCursorPositionBehavior);
    }

    public void setInvalidCdcCursorPositionBehavior(SourcePostgresInvalidCDCPositionBehaviorAdvanced invalidCdcCursorPositionBehavior) {
        this.invalidCdcCursorPositionBehavior = invalidCdcCursorPositionBehavior == null ? null : invalidCdcCursorPositionBehavior.getId();
    }


    public void setInitialLoadTimeoutHours(Long initialLoadTimeoutHours) {
        this.initialLoadTimeoutHours = initialLoadTimeoutHours;
    }

    public void setQueueSize(Long queueSize) {
        this.queueSize = queueSize;
    }

    public void setInvalidCdcCursorPositionBehavior(String invalidCdcCursorPositionBehavior) {
        this.invalidCdcCursorPositionBehavior = invalidCdcCursorPositionBehavior;
    }

    public void setInitialWaitingSeconds(Long initialWaitingSeconds) {
        this.initialWaitingSeconds = initialWaitingSeconds;
    }
}