package com.tehbeard.beardach.datasource.json;

import com.flowpowered.math.vector.Vector3d;
import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.utils.sponge.SpongeUtils;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

/**
 * Allows location's to be transformed to/from JSON
 * 
 * @author James
 * 
 */
public class LocationJSONParser implements JsonSerializer<Location>, JsonDeserializer<Location> {

    @Override
    public JsonElement serialize(Location location, Type type, JsonSerializationContext context) {
        System.out.println(location.toString());
        JsonObject element = new JsonObject();
        element.addProperty("world", location.getExtent() instanceof World ? ((World)location.getExtent()).getName() : null);
        element.addProperty("x", location.getBlockPosition().getX());
        element.addProperty("y", location.getBlockPosition().getY());
        element.addProperty("z", location.getBlockPosition().getZ());
        return element;
    }

    @Override
    public Location deserialize(JsonElement e, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject l = (JsonObject) e;
        return new Location(
                BeardAch.getGame().getServer().getWorld(l.get("world").getAsString()).get(), 
                new Vector3d(
                        l.get("x").getAsDouble(), 
                        l.get("y").getAsDouble(), 
                        l.get("z").getAsDouble()
                )
        );

    }

}
