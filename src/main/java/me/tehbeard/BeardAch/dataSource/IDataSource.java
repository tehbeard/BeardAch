package me.tehbeard.BeardAch.dataSource;
import java.util.HashSet;

import me.tehbeard.BeardAch.achievement.*;
/**
 * Represents a datasource for achievements
 * @author James
 *
 */
public interface IDataSource {

	public void loadAchievements();
	
	public HashSet<AchievementPlayerLink> getPlayersAchievements(String Player);
	
	public void setPlayersAchievements(String player,String achievement);
	
    public void flush();
    
    public void clearAchievements(String player);
    
    public void dumpFancy();
}
