package com.tehbeard.beardach.datasource;

import com.tehbeard.beardach.achievement.AchievementPlayerLink;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a datasource for achievements
 * 
 * @author James
 * 
 */
public interface IDataSource {

    public Set<AchievementPlayerLink> getPlayersAchievements(UUID Player);

    public void setPlayersAchievements(UUID player, String achievement);

    public void flush();

    public void clearAchievements(UUID player);

    public void dumpFancy();
    
    public List<String> getPlayers();
}
