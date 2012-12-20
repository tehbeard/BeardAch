package me.tehbeard.BeardAch.dataSource.json;

import java.lang.reflect.Type;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocationJSONParser implements JsonSerializer<Location>,JsonDeserializer<Location>{

    public JsonElement serialize(Location location, Type type,
            JsonSerializationContext context) {
        System.out.println(location.toString());
        JsonObject element = new JsonObject();
        element.addProperty("world", location.getWorld().getName());
        element.addProperty("x", location.getX());
        element.addProperty("y", location.getY());
        element.addProperty("z", location.getZ());
        element.addProperty("yaw", location.getYaw());
        element.addProperty("pitch", location.getPitch());
        return element;
    }

    public Location deserialize(JsonElement e, Type type,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject l = (JsonObject) e;
        return new Location(Bukkit.getWorld(l.get("world").getAsString()),
                l.get("x").getAsDouble(),
                l.get("y").getAsDouble(),
                l.get("z").getAsDouble(),
                l.get("yaw").getAsFloat(),
                l.get("pitch").getAsFloat()
                );
        
    }

}
