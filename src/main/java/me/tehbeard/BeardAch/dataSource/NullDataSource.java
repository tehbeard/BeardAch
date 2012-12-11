package me.tehbeard.BeardAch.dataSource;

import java.util.HashSet;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;

@DataSourceDescriptor(tag="null",version="1.0")
public class NullDataSource implements IDataSource{



	public NullDataSource(){
		BeardAch.printCon("Loading Null DataSource");
	}


	public HashSet<AchievementPlayerLink> getPlayersAchievements(String Player) {
		// TODO Auto-generated method stub
		HashSet<AchievementPlayerLink> d = new HashSet<AchievementPlayerLink>();
		//d.add("test");
		return d;
	}

	public void setPlayersAchievements(String player,
			String achievement) {
		// TODO Auto-generated method stub
		BeardAch.printCon("[" + player + "] stored "+ achievement);

	}

	public void flush() {
		// TODO Auto-generated method stub

	}

	public void clearAchievements(String player) {
		// TODO Auto-generated method stub

	}


	public void dumpFancy() {
		// TODO Auto-generated method stub
		
	}

}
