package com.tehbeard.beardach.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tehbeard.beardach.BeardAch;

/**
 * Controls reloading the achievements list
 * 
 * @author james
 * 
 */
public class AchReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

        if (!sender.hasPermission("beardach.reload"))
            return true;
        sender.sendMessage("Reloading Achievements");
        BeardAch.instance().getAchievementManager().loadAchievements();
        sender.sendMessage("Reloaded Achievements");
        return true;
    }

}
