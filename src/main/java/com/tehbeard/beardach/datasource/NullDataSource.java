package com.tehbeard.beardach.datasource;

import java.util.HashSet;
import java.util.Set;

import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.AchievementPlayerLink;
import com.tehbeard.beardach.annotations.DataSourceDescriptor;

@DataSourceDescriptor(tag = "test", version = "1.0")
public class NullDataSource implements IDataSource {

    public NullDataSource() {
        BeardAch.instance().getLogger().info("Loading Null DataSource");
    }

    @Override
    public Set<AchievementPlayerLink> getPlayersAchievements(String Player) {
        HashSet<AchievementPlayerLink> d = new HashSet<AchievementPlayerLink>();
        // d.add("test");
        return d;
    }

    @Override
    public void setPlayersAchievements(String player, String achievement) {
        BeardAch.instance().getLogger().fine("[" + player + "] stored " + achievement);

    }

    @Override
    public void flush() {

    }

    @Override
    public void clearAchievements(String player) {

    }

    @Override
    public void dumpFancy() {

    }

}
