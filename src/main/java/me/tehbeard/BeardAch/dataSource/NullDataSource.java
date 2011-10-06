package me.tehbeard.BeardAch.dataSource;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.bukkit.util.config.ConfigurationNode;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.AchievementManager;
import me.tehbeard.BeardAch.achievement.rewards.CommandReward;
import me.tehbeard.BeardAch.achievement.triggers.*;

public class NullDataSource implements IDataSource{



	public HashSet<Achievement> getAchievements() {
		// TODO Auto-generated method stub
		for(Entry<String, ConfigurationNode> e : BeardAch.config.getNodes("achievements").entrySet()){


			String name = e.getValue().getString("name");
			String descrip = e.getValue().getString("descrip");
			BeardAch.printDebugCon("Loading achievement " + name);

			Achievement ach = new Achievement(name, descrip);
			
			for(String trig: e.getValue().getStringList("triggers", new LinkedList<String>())){
				String[] part = trig.split("\\|");
				if(part.length==2){
					BeardAch.printDebugCon("Trigger => " + trig); 
					if(part[0].equals("stat")){
						ach.addTrigger(StatCheckTrigger.getInstance(part[1]));
					}else if(part[0].equals("perm")){
						ach.addTrigger(PermCheckTrigger.getInstance(part[1]));
					}else if(part[0].equals("cuboid")){
						ach.addTrigger(CuboidCheckTrigger.getInstance(part[1]));
					}else if(part[0].equals("ach")){
						ach.addTrigger(AchCheckTrigger.getInstance(part[1]));
					}
				}
				else
				{
					BeardAch.printCon("ERROR! MALFORMED TRIGGER FOR ACHIEVEMENT " + name);
				}
			}

			for(String reward: e.getValue().getStringList("rewards", new LinkedList<String>())){
				String[] part = reward.split("\\|");
				if(part.length==2){
					BeardAch.printDebugCon("Reward => " + reward); 
					
					if(part[0].equals("comm")){
					ach.addReward(CommandReward.getInstance(part[1]));
					}
				}
				else
				{
					BeardAch.printCon("ERROR! MALFORMED REWARD FOR ACHIEVEMENT " + name);
				}
			}
			
			AchievementManager.addAchievement(ach);
			BeardAch.printDebugCon("Loaded achievement " + name);
		}
		return null;
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
		BeardAch.printCon("[" + player + "]");
		BeardAch.printCon(achievement);
		
	}

}
