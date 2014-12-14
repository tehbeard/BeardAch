//package com.tehbeard.beardach.commands;
//
///**
// * Command for testing via command block if a player has unlocked an achievement
// * @author James
// *
// */
//public class TestAchCommand {
//
//    @Command(aliases = "help", desc = "Displays help information")
//    public boolean onCommand(CommandSender sender, Command cmd, String cmdLbl, String[] args) {
//        if(args.length != 2){
//            sender.sendMessage("Must supply a player and achievement Id to check");
//            return false;
//        }
//        if(sender instanceof BlockCommandSender == false){
//            sender.sendMessage("This command is for command blocks only.");
//            return false;
//        }
//        return manager.playerHas(Bukkit.getOfflinePlayer(args[0]).getUniqueId(), args[1]);
//    }
//
//  
//
//}
