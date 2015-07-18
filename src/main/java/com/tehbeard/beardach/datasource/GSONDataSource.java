package com.tehbeard.beardach.datasource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.AchievementPlayerLink;
import com.tehbeard.beardach.annotations.DataSourceDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@DataSourceDescriptor(tag = "json", version = "2.0")
public class GSONDataSource implements IDataSource {
    
    public class AchievementRecord {
        @Expose
        public UUID uuid;
        @Expose
        public Set<AchievementPlayerLink> achievements = new HashSet<AchievementPlayerLink>();
        
        public AchievementRecord(){
            
        }
        public AchievementRecord(UUID uuid){
            this.uuid = uuid;
        }
    }

    private final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    private List<AchievementRecord> data = new ArrayList<AchievementRecord>();
    private final File dbFile;

    public GSONDataSource() throws FileNotFoundException, IOException {
        dbFile = new File(BeardAch.getDataFolder(), "database.json");
        dbFile.createNewFile();
        List<AchievementRecord> d = gson.fromJson(new JsonReader(new FileReader(dbFile)), new TypeToken<List<AchievementRecord>>() {}.getType());;
        if(d != null){
            data = d;
        }
        
    }

    @Override
    public Set<AchievementPlayerLink> getPlayersAchievements(UUID player) {
        for(AchievementRecord d : data){
            if(d.uuid.equals(player)){
                return d.achievements;
            }
        }
        AchievementRecord d = new AchievementRecord(player);
        data.add(d);
        return d.achievements;
    }

    @Override
    public void setPlayersAchievements(UUID player, String achievement) {
        getPlayersAchievements(player).add(new AchievementPlayerLink(achievement));
    }

    @Override
    public void flush() {
        try {
            JsonWriter jw = new JsonWriter(new FileWriter(dbFile));
            jw.setIndent("  ");
            gson.toJson(data, new TypeToken<List<AchievementRecord>>() {
            }.getType(), jw);
            jw.flush();
            jw.close();
        } catch (IOException ex) {
            Logger.getLogger(GSONDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void clearAchievements(UUID player) {
        getPlayersAchievements(player).clear();
    }

    @Override
    public void dumpFancy() {
        throw new UnsupportedOperationException("Not supported yet."); // To
                                                                       // change
                                                                       // body
                                                                       // of
                                                                       // generated
                                                                       // methods,
                                                                       // choose
                                                                       // Tools
                                                                       // |
                                                                       // Templates.
    }

    @Override
    public List<String> getPlayers() {
        List<String> names = new ArrayList<String>(data.size());
        for(AchievementRecord d : data){
            names.add(BeardAch.getGame().getServer().getPlayer(d.uuid).get().getName());
        }
        return names;
    }
}