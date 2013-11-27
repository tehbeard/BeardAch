package com.tehbeard.beardach.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tehbeard.beardach.BeardAch;

public class AchAdminCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

        if (args.length == 0)
            return false;

        if (args[0].startsWith("dumpmeta")) {
            if (!sender.hasPermission("beardach.reload.fancy"))
                return true;
            sender.sendMessage("Dumping achievement metadata to database");
            BeardAch.instance().getAchievementManager().dumpFancy();
        }

        if (args[0].startsWith("clearmeta")) {
            sender.sendMessage("not implemented");
        }

        return true;
    }

}
