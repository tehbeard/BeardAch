package me.tehbeard.BeardAch.commands;

import me.tehbeard.BeardAch.BeardAch;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AchAdminCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String lbl,
			String[] args) {
	    
	    if(args.length == 0){return false;}
	    
	    if(args[0].startsWith("dumpmeta")){
	        if(!sender.hasPermission("beardach.reload.fancy")){return true;}
	        sender.sendMessage("Dumping achievement metadata to database");
	        BeardAch.self.getAchievementManager().database.dumpFancy();
	    }
	    
	    if(args[0].startsWith("clearmeta")){
	        sender.sendMessage("not implemented");
	    }
	    
	    return true;
	}

	
}
