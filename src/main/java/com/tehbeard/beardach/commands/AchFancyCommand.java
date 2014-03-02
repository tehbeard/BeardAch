package com.tehbeard.beardach.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tehbeard.beardach.BeardAch;

/**
 * Controls exporting the achievement data to an external source.
 * 
 * @author james
 * 
 */
public class AchFancyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command lbl, String cmd, String[] args) {
        if (!sender.hasPermission("beardach.reload.fancy"))
            return true;
        sender.sendMessage("Dumping fancy achievement names");
        BeardAch.instance().getAchievementManager().dumpFancy();
        return true;
    }

}