package com.tehbeard.beardach.datasource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@DataSourceDescriptor(tag = "json", version = "2.0")
public class GSONDataSource implements IDataSource {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    private Map<UUID, Set<AchievementPlayerLink>> data = new HashMap<UUID, Set<AchievementPlayerLink>>();
    private final File dbFile;

    public GSONDataSource() throws FileNotFoundException, IOException {
        dbFile = new File(BeardAch.instance().getDataFolder(), "database.json");
        dbFile.createNewFile();
        data = gson.fromJson(new JsonReader(new FileReader(dbFile)), new TypeToken<Map<String, Set<AchievementPlayerLink>>>() {
        }.getType());
    }

    @Override
    public Set<AchievementPlayerLink> getPlayersAchievements(UUID player) {
        if (!data.containsKey(player)) {
            data.put(player, new HashSet<AchievementPlayerLink>());
        }

        return data.get(player);
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
            gson.toJson(data, new TypeToken<Map<String, Set<AchievementPlayerLink>>>() {
            }.getType(), jw);
            jw.flush();
            jw.close();
        } catch (IOException ex) {
            Logger.getLogger(GSONDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void clearAchievements(UUID player) {
        data.put(player, new HashSet<AchievementPlayerLink>());
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
        throw new UnsupportedOperationException("Needs to be fixed!");
    }
}