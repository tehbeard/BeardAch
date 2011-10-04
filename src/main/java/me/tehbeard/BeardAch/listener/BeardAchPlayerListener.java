package me.tehbeard.BeardAch.listener;

import me.tehbeard.BeardAch.achievement.AchievementManager;
import me.tehbeard.BeardAch.achievement.triggers.CuboidCheckTrigger;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class BeardAchPlayerListener extends PlayerListener {

	public void onPlayerMove(PlayerMoveEvent event){
		if(event.getTo().getBlockX() != event.getFrom().getBlockX() ||
				event.getTo().getBlockY() != event.getFrom().getBlockY() ||	
				event.getTo().getBlockZ() != event.getFrom().getBlockZ()
				){
		}
	}
	
	@Override
	public void onPlayerLogin(PlayerLoginEvent event) {
		// TODO Auto-generated method stub
		AchievementManager.loadAchievements(event.getPlayer().getName());
		
	}
	
	
}
