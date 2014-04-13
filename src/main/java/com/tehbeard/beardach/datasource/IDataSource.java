package com.tehbeard.beardach.datasource;

import java.util.List;
import java.util.Set;

import org.bukkit.OfflinePlayer;

import com.tehbeard.beardach.achievement.AchievementPlayerLink;

/**
 * Represents a datasource for achievements
 * 
 * @author James
 * 
 */
public interface IDataSource {

    public Set<AchievementPlayerLink> getPlayersAchievements(OfflinePlayer Player);

    public void setPlayersAchievements(OfflinePlayer player, String achievement);

    public void flush();

    public void clearAchievements(OfflinePlayer player);

    public void dumpFancy();
    
    public List<String> getPlayers();
}
