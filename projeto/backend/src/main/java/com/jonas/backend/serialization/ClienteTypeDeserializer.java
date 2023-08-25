/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonas.backend.serialization;

import com.jonas.backend.enums.ClienteType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class ClienteTypeDeserializer extends JsonDeserializer<ClienteType> {

    @Override
    public ClienteType deserialize(JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String value = node.asText();

        if (!value.equals("PF") && !value.equals("PJ")) {
            throw new IllegalArgumentException("Invalid ClienteType");
        }

        return ClienteType.valueOf(value);

    }

}
