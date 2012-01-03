package me.tehbeard.BeardAch.commands;

import java.util.List;

import me.tehbeard.BeardAch.BeardAch;
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

				//SIMPLE PAGINATION, ONLY SHOW LAST 5 ACHIEVEMENTS
				List<AchievementPlayerLink> list = AchievementManager.getAchievements(player.getName());
				AchievementPlayerLink a;
				
				int pageSize = Math.min(5, list.size());
				
				player.sendMessage(ChatColor.AQUA + "Unlocked Achievements:");
				for(int i=list.size()-pageSize;i<list.size();i++){
					a = list.get(i);
					player.sendMessage(ChatColor.WHITE + "#" + a.getAch().getId() + " "+ ChatColor.GOLD + a.getAch().getName() + " - " + ChatColor.WHITE + a.getDate().toString());
				}
				String msg = BeardAch.config.getString("ach.msg.ach", null);
				
				if(msg!=null){
					player.sendMessage(msg);
				}
				
			}else if(args.length ==1){
				Achievement a = AchievementManager.getAchievement(Integer.parseInt(args[0]));
				if(a!=null){
					player.sendMessage(ChatColor.GOLD + a.getName());
					player.sendMessage(ChatColor.BLUE + a.getDescrip());

					//if they have unlocked it, tell them when they did
					for( AchievementPlayerLink aLink:AchievementManager.getAchievements(player.getName())){
						if(aLink.getSlug().equals(a.getSlug())){
						player.sendMessage(ChatColor.WHITE  + "You Unlocked this: " + aLink.getDate().toString());
						}
					}
					
				}
			}

		}

		return true;
	}

}
