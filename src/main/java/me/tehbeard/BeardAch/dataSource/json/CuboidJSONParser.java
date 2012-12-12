package me.tehbeard.BeardAch.dataSource.json;

import java.lang.reflect.Type;

import org.bukkit.Location;

import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.AchievementLoader;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.utils.cuboid.Cuboid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CuboidJSONParser implements JsonSerializer<Cuboid>,JsonDeserializer<Cuboid>{

    private Gson gson = new GsonBuilder().create();
    
    public JsonElement serialize(Cuboid cuboid, Type type,
            JsonSerializationContext context) {
        return gson.toJsonTree(cuboid);
    }

    public Cuboid deserialize(JsonElement element, Type type,
            JsonDeserializationContext context) throws JsonParseException {
        
        return gson.fromJson(element,Cuboid.class);
        
    }

}
