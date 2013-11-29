/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tehbeard.beardach.datasource.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tehbeard.beardach.annotations.Configurable;

/**
 * 
 * @author James
 */
public class ClassBasedParser<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private final ClassCatalogue<T> catalogue;

    public ClassBasedParser(ClassCatalogue<T> catalogue) {
        this.catalogue = catalogue;
    }

    @Override
    public JsonElement serialize(T t, Type type, JsonSerializationContext context) {
        JsonElement element = context.serialize(t, t.getClass());
        element.getAsJsonObject().addProperty("_type", t.getClass().getAnnotation(Configurable.class).tag());
        return element;
    }

    @Override
    public T deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonElement key = element.getAsJsonObject().get("_type");

        if (key == null) {
            throw new AchievementParserException("Deserialization failed: Element lacks _type tag, " + element.getAsJsonObject().getAsString());
        }

        Class<? extends T> classType = catalogue.get(key.getAsString());

        if (classType == null) {
            throw new AchievementParserException("Cannot find class mapped to type " + key.getAsString());
        }

        return context.deserialize(element, classType);
    }

}
