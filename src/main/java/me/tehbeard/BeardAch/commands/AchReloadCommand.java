package me.tehbeard.BeardAch.commands;

import me.tehbeard.BeardAch.BeardAch;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


/**
 * Controls reloading the achievements list
 * @author james
 *
 */
public class AchReloadCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String lbl,
			String[] args) {

		if(!sender.hasPermission("beardach.reload")){return true;}
		sender.sendMessage("Reloading Achievements");
		BeardAch.self.getAchievementManager().loadAchievements();
		sender.sendMessage("Reloaded Achievements");
		return true;
	}


}
