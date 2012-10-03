package me.tehbeard.BeardAch.dataSource.configurable;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.help.HelpTopic;

public class ConfigurableHelpTopic extends HelpTopic{
    
    public ConfigurableHelpTopic(String name,Usage usage) {
        this.name = name;
        this.shortText = name + "|";
        boolean doColon = false;
        for(String arg : usage.arguments()){
            if(doColon){shortText+= ":";}
            shortText+= arg.split("|")[0];
            doColon = true;
                    
        }
        
        this.fullText = name + "[" + usage.packageName() + " Package]\n"+
        ChatColor.GOLD + "Dependencies:";
        for(String dep: usage.dependencies()){
            fullText +=dep + "\n";
        }
        if(usage.dependencies().length == 0){
            fullText +="none";
        }
        fullText += ChatColor.GOLD + "Usage:\n";
        for(String arg : usage.arguments()){
            fullText= arg + "\n";
        }
    }

    @Override
    public boolean canSee(CommandSender sender) {
        return sender.hasPermission("beardach.help");
    }

}
