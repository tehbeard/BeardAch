package com.tehbeard.beardach.datasource.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tehbeard.utils.cuboid.Cuboid;

/**
 * ..Is this even needed anymore?
 * 
 * @author James
 * 
 */
public class CuboidJSONParser implements JsonSerializer<Cuboid>, JsonDeserializer<Cuboid> {

    private Gson gson = new GsonBuilder().create();

    @Override
    public JsonElement serialize(Cuboid cuboid, Type type, JsonSerializationContext context) {
        return gson.toJsonTree(cuboid);
    }

    @Override
    public Cuboid deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {

        return gson.fromJson(element, Cuboid.class);

    }

}
