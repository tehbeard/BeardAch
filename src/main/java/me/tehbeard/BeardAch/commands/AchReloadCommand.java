package me.tehbeard.BeardAch.commands;

import me.tehbeard.BeardAch.achievement.AchievementManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AchReloadCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String lbl,
			String[] args) {

		if(!sender.hasPermission("beardach.reload")){return true;}
		sender.sendMessage("Reloading Achievements");
		AchievementManager.loadAchievements();
		sender.sendMessage("Reloaded Achievements");
		return true;
	}


}
