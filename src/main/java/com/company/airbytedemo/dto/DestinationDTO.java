package com.company.airbytedemo.dto;

import io.jmix.core.metamodel.annotation.JmixEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JmixEntity
public class DestinationDTO implements Serializable {
}
