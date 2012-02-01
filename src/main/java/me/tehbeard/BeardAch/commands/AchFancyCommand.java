package me.tehbeard.BeardAch.commands;

import me.tehbeard.BeardAch.BeardAch;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


/**
 * Controls exporting the achievement data to an external source.
 * @author james
 *
 */
public class AchFancyCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command lbl, String cmd,
			String[] args) {
		// TODO Auto-generated method stub
		if(!sender.hasPermission("beardach.reload.fancy")){return true;}
		sender.sendMessage("Dumping fancy achievement names");
		BeardAch.self.getAchievementManager().database.dumpFancy();
		return true;
	}

}
