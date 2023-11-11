package com.workoutbuilder.enterprise.adapter;

import com.google.gson.*;
import java.lang.reflect.Type;

public class LowercaseEnumTypeAdapter<E extends Enum<E>> implements JsonDeserializer<E> {

    private final Class<E> classOfE;

    public LowercaseEnumTypeAdapter(Class<E> classOfE) {
        this.classOfE = classOfE;
    }

    @Override
    public E deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return Enum.valueOf(classOfE, json.getAsString().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null; // or throw JsonParseException if you want to handle it as an error
        }
    }
}