package me.tehbeard.BeardAch.dataSource;
import java.util.HashSet;

import me.tehbeard.BeardAch.achievement.*;
/**
 * Represents a datasource for achievements
 * @author James
 *
 */
public interface IDataSource {

	public HashSet<Achievement> loadAchievements();
	
	public HashSet<String> getPlayersAchievements(String Player);
	
	public void setPlayersAchievements(String player,String achievement);
	
    public void flush();
    
    public void clearAchievements(String player);
}
