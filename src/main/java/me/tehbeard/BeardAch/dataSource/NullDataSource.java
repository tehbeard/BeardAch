package me.tehbeard.BeardAch.dataSource;

import java.util.HashSet;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;

public class NullDataSource implements IDataSource{

	public HashSet<Achievement> getAchievements() {
		// TODO Auto-generated method stub
		return null;
	}

	public HashSet<String> getPlayersAchievements(String Player) {
		// TODO Auto-generated method stub
		HashSet<String> d = new HashSet<String>();
		//d.add("test");
		return d;
	}

	public void setPlayersAchievements(String player,
			HashSet<String> achievements) {
		// TODO Auto-generated method stub
		BeardAch.printCon("[" + player + "]");
		for(String a:achievements){
		BeardAch.printCon(a);
	}
	}

}
