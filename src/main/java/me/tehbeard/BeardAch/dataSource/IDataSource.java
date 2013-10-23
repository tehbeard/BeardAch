package me.tehbeard.BeardAch.dataSource;

import java.util.Set;

import me.tehbeard.BeardAch.achievement.*;

/**
 * Represents a datasource for achievements
 *
 * @author James
 *
 */
public interface IDataSource {

    public Set<AchievementPlayerLink> getPlayersAchievements(String Player);

    public void setPlayersAchievements(String player, String achievement);

    public void flush();

    public void clearAchievements(String player);

    public void dumpFancy();
}
