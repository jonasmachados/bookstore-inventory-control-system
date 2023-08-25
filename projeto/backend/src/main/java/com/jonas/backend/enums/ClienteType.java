package com.jonas.backend.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jonas.backend.serialization.ClienteTypeDeserializer;

@JsonDeserialize(using = ClienteTypeDeserializer.class)
public enum ClienteType {

    PJ,
    PF

}
