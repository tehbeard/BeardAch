package com.tehbeard.beardach.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.AchievementPlayerLink;

public class AchCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {

                // SIMPLE PAGINATION, ONLY SHOW LAST 5 ACHIEVEMENTS
                List<AchievementPlayerLink> list = BeardAch.instance().getAchievementManager().getAchievements(player);
                AchievementPlayerLink a;

                int pageSize = Math.min(5, list.size());

                player.sendMessage(ChatColor.AQUA + "Unlocked Achievements:");
                for (int i = list.size() - pageSize; i < list.size(); i++) {
                    a = list.get(i);
                    player.sendMessage(ChatColor.WHITE + "#" + a.getAch().getId() + " " + ChatColor.GOLD + a.getAch().getName() + " - " + ChatColor.WHITE + a.getDate().toString());
                }
                String msg = BeardAch.instance().getConfig().getString("ach.msg.ach", null);

                if (msg != null) {
                    player.sendMessage(msg);
                }

            } else if (args.length == 1) {
                Achievement a = BeardAch.instance().getAchievementManager().getAchievement(Integer.parseInt(args[0]));
                if (a != null) {
                    player.sendMessage(ChatColor.GOLD + a.getName());
                    player.sendMessage(ChatColor.BLUE + a.getDescrip());

                    // if they have unlocked it, tell them when they did
                    for (AchievementPlayerLink aLink : BeardAch.instance().getAchievementManager().getAchievements(player)) {
                        if (aLink.getSlug().equals(a.getSlug())) {
                            player.sendMessage(ChatColor.WHITE + "You Unlocked this: " + aLink.getDate().toString());
                        }
                    }

                }
            }

        }

        return true;
    }

}
