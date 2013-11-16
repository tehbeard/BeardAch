package me.tehbeard.BeardAch.dataSource;

import java.util.HashSet;
import java.util.Set;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;

@DataSourceDescriptor(tag="test",version="1.0")
public class NullDataSource implements IDataSource{



	public NullDataSource(){
		BeardAch.instance().getLogger().info("Loading Null DataSource");
	}


	public Set<AchievementPlayerLink> getPlayersAchievements(String Player) {
		HashSet<AchievementPlayerLink> d = new HashSet<AchievementPlayerLink>();
		//d.add("test");
		return d;
	}

	public void setPlayersAchievements(String player,
			String achievement) {
		BeardAch.instance().getLogger().fine("[" + player + "] stored "+ achievement);

	}

	public void flush() {

	}

	public void clearAchievements(String player) {

	}


	public void dumpFancy() {
		
	}

}
