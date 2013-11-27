<<<<<<< HEAD:src/main/java/com/tehbeard/beardach/dataSource/GSONDataSource.java
package com.tehbeard.beardach.dataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.AchievementPlayerLink;

@DataSourceDescriptor(tag = "json", version = "1.0")
public class GSONDataSource implements IDataSource {
    
    private Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    private Map<String, Set<AchievementPlayerLink>> data = new HashMap<String, Set<AchievementPlayerLink>>();
    private File dbFile;

    public GSONDataSource() {
        //BeardAch.instance().getLogger().warning("Alert, Yaml provider is now deprecated, json provider will take it's place in the future.");
        dbFile = new File(BeardAch.instance().getDataFolder(), "database.json");
    }

    @Override
    public Set<AchievementPlayerLink> getPlayersAchievements(String player) {
        if (!data.containsKey(player)) {
            data.put(player, new HashSet<AchievementPlayerLink>());
        }

        return data.get(player);
    }

    @Override
    public void setPlayersAchievements(String player, String achievement) {
        getPlayersAchievements(player).add(new AchievementPlayerLink(achievement));
    }

    @Override
    public void flush() {
        try {
            JsonWriter jw = new JsonWriter(new FileWriter(dbFile));
            jw.setIndent("  ");
            gson.toJson(data,new TypeToken<Map<String, Set<AchievementPlayerLink>>>(){}.getType(),jw);
            jw.flush();
            jw.close();
        } catch (IOException ex) {
            Logger.getLogger(GSONDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void clearAchievements(String player) {
        data.put(player, new HashSet<AchievementPlayerLink>());
    }

    @Override
    public void dumpFancy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
=======
package me.tehbeard.BeardAch.dataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;

@DataSourceDescriptor(tag = "json", version = "1.0")
public class GSONDataSource implements IDataSource {
    
    private Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    private Map<String, Set<AchievementPlayerLink>> data = new HashMap<String, Set<AchievementPlayerLink>>();
    private File dbFile;

    public GSONDataSource() throws FileNotFoundException, IOException {
        dbFile = new File(BeardAch.instance().getDataFolder(), "database.json");
        dbFile.createNewFile();
        data = gson.fromJson(new JsonReader(new FileReader(dbFile)),new TypeToken<Map<String, Set<AchievementPlayerLink>>>(){}.getType());
    }

    @Override
    public Set<AchievementPlayerLink> getPlayersAchievements(String player) {
        if (!data.containsKey(player)) {
            data.put(player, new HashSet<AchievementPlayerLink>());
        }

        return data.get(player);
    }

    @Override
    public void setPlayersAchievements(String player, String achievement) {
        getPlayersAchievements(player).add(new AchievementPlayerLink(achievement));
    }

    @Override
    public void flush() {
        try {
            JsonWriter jw = new JsonWriter(new FileWriter(dbFile));
            jw.setIndent("  ");
            gson.toJson(data,new TypeToken<Map<String, Set<AchievementPlayerLink>>>(){}.getType(),jw);
            jw.flush();
            jw.close();
        } catch (IOException ex) {
            Logger.getLogger(GSONDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void clearAchievements(String player) {
        data.put(player, new HashSet<AchievementPlayerLink>());
    }

    @Override
    public void dumpFancy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
>>>>>>> develop:src/main/java/me/tehbeard/BeardAch/dataSource/GSONDataSource.java
