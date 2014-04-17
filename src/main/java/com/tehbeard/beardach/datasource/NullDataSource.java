package com.tehbeard.beardach.datasource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.OfflinePlayer;

import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.AchievementPlayerLink;
import com.tehbeard.beardach.annotations.DataSourceDescriptor;

@DataSourceDescriptor(tag = "test", version = "1.0")
public class NullDataSource implements IDataSource {

    public NullDataSource() {
        BeardAch.instance().getLogger().info("Loading Null DataSource");
    }

    @Override
    public Set<AchievementPlayerLink> getPlayersAchievements(OfflinePlayer Player) {
        HashSet<AchievementPlayerLink> d = new HashSet<AchievementPlayerLink>();
        // d.add("test");
        return d;
    }

    @Override
    public void setPlayersAchievements(OfflinePlayer player, String achievement) {
        BeardAch.instance().getLogger().fine("[" + player.getName() + "] stored " + achievement);

    }

    @Override
    public void flush() {

    }

    @Override
    public void clearAchievements(OfflinePlayer player) {

    }

    @Override
    public void dumpFancy() {

    }

    @Override
    public List<String> getPlayers() {
        return new ArrayList<String>();
    }

}
