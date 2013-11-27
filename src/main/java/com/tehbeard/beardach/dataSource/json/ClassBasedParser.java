/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tehbeard.beardach.datasource.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.annotations.Configurable;
import java.util.logging.Level;

/**
 *
 * @author James
 */
public class ClassBasedParser<T> implements JsonSerializer<T>,JsonDeserializer<T>{
    
    private final ClassCatalogue<T> catalogue;
    
    public ClassBasedParser(ClassCatalogue<T> catalogue){
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
        try{
            JsonElement key = element.getAsJsonObject().get("_type");
            if(key == null){
                BeardAch.instance().getLogger().log(Level.SEVERE, "Deserialization failed: Element lacks _type tag, {0}", element.getAsJsonObject().getAsString());
            }
            return context.deserialize(element, catalogue.get(key.getAsString()));
            }
            catch (NoClassDefFoundError e){
            	BeardAch.instance().getLogger().log(Level.SEVERE, "Deserialization failed: {0}", element.getAsJsonObject().get("_type").getAsString());
            	BeardAch.instance().getLogger().severe("Dumping JSON");
            	BeardAch.instance().getLogger().severe(element.toString());
            	BeardAch.instance().getLogger().severe("END Dumping JSON");
            }
            return null;
    }
    
}
