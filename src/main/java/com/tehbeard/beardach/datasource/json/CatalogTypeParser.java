/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tehbeard.beardach.datasource.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tehbeard.beardach.BeardAch;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.spongepowered.api.CatalogType;

/**
 *
 * @author James
 */
public class CatalogTypeParser implements JsonSerializer<CatalogType>, JsonDeserializer<CatalogType>{

    @Override
    public JsonElement serialize(CatalogType t, Type type, JsonSerializationContext ctx) {
        System.out.println("Type is " + type.toString());
        return ctx.serialize(t.getId());
    }

    @Override
    public CatalogType deserialize(JsonElement je, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        try {
            Class<? extends CatalogType> catalogClass = (Class<? extends CatalogType>) Class.forName(type.toString().split(" ")[1]);
            return BeardAch.getGame().getRegistry().getType(catalogClass, je.getAsString()).get();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CatalogTypeParser.class.getName()).log(Level.SEVERE, null, ex);
                throw new JsonParseException("Could not load required class for catalog", ex);
            }
    }
}
