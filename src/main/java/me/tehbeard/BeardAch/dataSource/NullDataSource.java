package me.tehbeard.BeardAch.dataSource;

import java.util.HashSet;

import me.tehbeard.BeardAch.BeardAch;


public class NullDataSource extends AbstractDataSource{



	public NullDataSource(){
		BeardAch.printCon("Loading Null DataSource");
	}


	public HashSet<String> getPlayersAchievements(String Player) {
		// TODO Auto-generated method stub
		HashSet<String> d = new HashSet<String>();
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

}
