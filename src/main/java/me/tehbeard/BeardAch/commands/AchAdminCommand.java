package me.tehbeard.BeardAch.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AchAdminCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String lbl,
			String[] args) {
		if(sender instanceof Player){
			Player player = (Player)sender;
			if(args.length==0){
				if(args[0].equals("clear")){
					//TODO: add clear
				}
			}
		}
		return false;
	}

	
}
