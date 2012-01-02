package me.tehbeard.BeardAch.achievement;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;

/**
 * Represents an achievement.
 * @author James
 *
 */
public class Achievement {

	private String slug;
	private String name;
	private String descrip;
	private int id = 0;
	private static int nextId = 1;
	private HashSet<ITrigger> triggers = new HashSet<ITrigger>();
	private HashSet<IReward> rewards = new HashSet<IReward>();
	public Achievement(String slug,String name,String descrip) {
		this.slug = slug;
		this.name = name;
		this.descrip = descrip;
		id = nextId;
		nextId ++;
	}
	
	public static void resetId(){
	  nextId = 1;
	}
	public int getId() {
		return id;
	}
	
	public String getSlug() {
		return slug;
	}

	public String getName(){
		return name;
	}

	public String getDescrip(){
		return descrip;
	}

	public void addTrigger(ITrigger trigger){
		if(trigger==null){return;}
		triggers.add(trigger);
	}

	public void addReward(IReward reward){
		if(reward==null){return;}
		rewards.add(reward);
	}

	public boolean checkAchievement(Player player){
		if(player==null){
			return false;
		}

		for(AchievementPlayerLink link : AchievementManager.playerHasCache.get(player.getName())){
			if(link.getSlug().equals(slug)){
				return false;
			}
		}
		for(ITrigger trigger:triggers){
			if(!trigger.checkAchievement(player)){
				return false;
			}
		}
		for(IReward reward:rewards){
			reward.giveReward(player);
		}

		if(BeardAch.config.getBoolean("ach.msg.send.broadcast", false) && !BeardAch.config.getBoolean("ach.msg.send.person", true)){
			Bukkit.broadcastMessage(BeardAch.config.getString("ach.msg.broadcast", (ChatColor.AQUA + "<PLAYER> " + ChatColor.WHITE + "Unlocked: " + ChatColor.GOLD + "<ACH>")).replace("<ACH>", name).replace("<PLAYER>",player.getName()));

		}
		if(BeardAch.config.getBoolean("ach.msg.send.person", true)){
			player.sendRawMessage(BeardAch.config.getString("ach.msg.person", "Achievement Unlocked: " + ChatColor.GOLD + "<ACH>").replace("<ACH>", name).replace("<PLAYER>",player.getName()));
		}

		return true;
	}

	public HashSet<ITrigger> getTrigs(){
		return triggers;
	}



}
