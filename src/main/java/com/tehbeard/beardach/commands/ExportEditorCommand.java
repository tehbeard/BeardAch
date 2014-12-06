package com.tehbeard.beardach.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tehbeard.beardach.BeardAch;

public class ExportEditorCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdlbl, String[] args) {
        if(!sender.isOp()){return true;}
        sender.sendMessage("Exporting BeardAch editor");
        BeardAch.exportEditor();
        return true;
    }

}
