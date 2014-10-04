package com.tehbeard.beardach.datasource;

import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.AchievementPlayerLink;
import com.tehbeard.beardach.annotations.DataSourceDescriptor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;

@DataSourceDescriptor(tag = "test", version = "1.0")
public class NullDataSource implements IDataSource {

    public NullDataSource() {
        BeardAch.instance().getLogger().info("Loading Null DataSource");
    }

    @Override
    public Set<AchievementPlayerLink> getPlayersAchievements(UUID Player) {
        HashSet<AchievementPlayerLink> d = new HashSet<AchievementPlayerLink>();
        return d;
    }

    @Override
    public void setPlayersAchievements(UUID player, String achievement) {
        BeardAch.instance().getLogger().log(Level.FINE, "[{0}] stored {1}", new Object[]{player.toString(), achievement});

    }

    @Override
    public void flush() {

    }

    @Override
    public void clearAchievements(UUID player) {

    }

    @Override
    public void dumpFancy() {

    }

    @Override
    public List<String> getPlayers() {
        return new ArrayList<String>();
    }

}
