package com.tehbeard.beardach.datasource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.AchievementPlayerLink;
import com.tehbeard.beardach.annotations.DataSourceDescriptor;

@DataSourceDescriptor(tag = "file", version = "1.4")
public class YamlDataSource implements IDataSource {

    YamlConfiguration db;
    File dbFile;
    File fancy;

    public YamlDataSource() {
        Plugin plugin = BeardAch.instance();
        dbFile = new File(plugin.getDataFolder(), "database.yml");
        fancy = new File(plugin.getDataFolder(), "ach.html");
        db = YamlConfiguration.loadConfiguration(dbFile);
        plugin.getLogger().warning("Alert, Yaml provider is now deprecated, json provider will take it's place in the future.");
    }

    @Override
    public void dumpFancy() {

        try {
            FileWriter fwrite = new FileWriter(fancy);
            fwrite.write("<html>\n" + "<head>\n" + "<title>Achievements</title>\n" + "</head>\n" + "<body>\n");
            ConfigurationSection dbb = db.getConfigurationSection("database");
            if (dbb == null) {
            }
            Set<String> players = dbb.getKeys(false);
            for (String player : players) {
                Set<AchievementPlayerLink> achs = getPlayersAchievements(player);
                if (achs.size() > 0) {
                    fwrite.write("<h2>" + player + "</h2>\n");
                    fwrite.write("<table>\n" + "<tr><th>Achievement</th><th>Date unlocked</th></tr\n");
                    for (AchievementPlayerLink link : achs) {
                        fwrite.write("<tr>\n" + "<td>" + link.getAch().getName() + "</td>\n" + "<td>" + link.getDate() + "</td>\n" + "</tr>\n");
                    }
                    fwrite.write("</table>\n");
                }
            }

            fwrite.write("</body></html>\n");
            fwrite.close();
        } catch (IOException e) {
            System.out.println("I/O error occured while trying to write out to file");
        }

    }

    @Override
    public Set<AchievementPlayerLink> getPlayersAchievements(String player) {
        HashSet<AchievementPlayerLink> ret = new HashSet<AchievementPlayerLink>();
        ConfigurationSection playerData = db.getConfigurationSection("database." + player);
        if (playerData != null) {
            Set<String> keys = playerData.getKeys(false);
            if (keys != null) {
                for (String key : keys) {
                    long unlock = playerData.getLong(key);
                    ret.add(new AchievementPlayerLink(key, new Timestamp(unlock)));
                }
            }
        }
        return ret;
    }

    @Override
    public void setPlayersAchievements(String player, String achievement) {
        db.set("database." + player + "." + achievement, System.currentTimeMillis());
    }

    @Override
    public void flush() {

        try {
            db.save(dbFile);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void clearAchievements(String player) {
        db.set("database." + player, null);
    }

}
