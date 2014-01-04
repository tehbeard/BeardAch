package com.tehbeard.beardach.commands;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tehbeard.beardach.achievement.AchievementManager;

/**
 * Command for testing via command block if a player has unlocked an achievement
 * @author James
 *
 */
public class TestAchCommand implements CommandExecutor {

    private final AchievementManager manager;
    
    
    /**
     * @param manager
     */
    public TestAchCommand(AchievementManager manager) {
        this.manager = manager;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLbl, String[] args) {
        if(args.length != 2){
            sender.sendMessage("Must supply a player and achievement Id to check");
            return false;
        }
        if(sender instanceof BlockCommandSender == false){
            sender.sendMessage("This command is for command blocks only.");
            return false;
        }
        return manager.playerHas(args[0], args[1]);
    }

  

}
