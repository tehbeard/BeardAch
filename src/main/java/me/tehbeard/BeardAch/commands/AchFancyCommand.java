package me.tehbeard.BeardAch.commands;

import me.tehbeard.BeardAch.achievement.AchievementManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AchFancyCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command lbl, String cmd,
			String[] args) {
		// TODO Auto-generated method stub
		if(!sender.hasPermission("beardach.reload.fancy")){return true;}
		sender.sendMessage("Dumping fancy achievement names");
		AchievementManager.database.dumpFancy();
		return true;
	}

}
