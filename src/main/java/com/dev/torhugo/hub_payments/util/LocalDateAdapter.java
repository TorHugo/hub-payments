package com.dev.torhugo.hub_payments.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;


/**
 * Gson doesn't automatically handle Java 8 types, such as LocalDate. <br/>
 * You'll need to register a custom adapter so that Gson knows how to serialize/deserialize LocalDate objects.
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    @Override
    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.toString());
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDate.parse(json.getAsString());
    }
}
