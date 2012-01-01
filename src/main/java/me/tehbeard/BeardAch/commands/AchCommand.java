package me.tehbeard.BeardAch.commands;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.AchievementManager;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AchCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		if(sender instanceof Player){
			Player player = (Player)sender;
			if(args.length == 0){

				player.sendMessage(ChatColor.AQUA + "Unlocked Achievements:");
				for( AchievementPlayerLink a:AchievementManager.getAchievements(player.getName())){


					player.sendMessage(ChatColor.WHITE + "#" + a.getAch().getId() + " "+ ChatColor.GOLD + a.getAch().getName() + " - " + ChatColor.WHITE + a.getDate().toString());
				}
			}else if(args.length ==1){
				Achievement a = AchievementManager.getAchievement(Integer.parseInt(args[0]));
				if(a!=null){
					player.sendMessage(ChatColor.GOLD + a.getName());
					player.sendMessage(ChatColor.BLUE + a.getDescrip());
				}
			}

		}

		return true;
	}

}
