package me.tehbeard.BeardAch.listener;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.ChunkCache;
import me.tehbeard.BeardAch.achievement.AchievementManager;


import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class BeardAchPlayerListener extends PlayerListener {

	public void onPlayerMove(PlayerMoveEvent event){
		if(event.getTo().getBlockX() != event.getFrom().getBlockX() ||
				event.getTo().getBlockY() != event.getFrom().getBlockY() ||	
				event.getTo().getBlockZ() != event.getFrom().getBlockZ()
				){
			BeardAch.printDebugCon("Player moved, checking chunk Cache");
			ChunkCache.checkLocation(event.getPlayer());
		}
	}
	
	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		// TODO Auto-generated method stub
		AchievementManager.loadPlayersAchievements(event.getPlayer().getName());
		
	}
	
	

	
	
}
