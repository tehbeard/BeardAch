//package com.tehbeard.beardach.commands;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import org.bukkit.ChatColor;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandSender;
//import org.bukkit.command.TabExecutor;
//import org.spongepowered.api.entity.player.Player;
//
//import com.tehbeard.beardach.BeardAch;
//import com.tehbeard.beardach.achievement.Achievement;
//import com.tehbeard.beardach.achievement.AchievementPlayerLink;
//
//public class AchCommand implements TabExecutor {
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//
//        if (sender instanceof Player) {
//            Player player = (Player) sender;
//            if (args.length == 0) {
//
//                List<AchievementPlayerLink> list = BeardAch.getAchievementManager().getAchievements(player.getUniqueId());
//                AchievementPlayerLink a;
//
//                int pageSize = Math.min(5, list.size());
//
//                player.sendMessage(ChatColor.AQUA + "Unlocked Achievements:");
//                for (int i = list.size() - pageSize; i < list.size(); i++) {
//                    a = list.get(i);
//                    player.sendMessage(ChatColor.WHITE + "#" + a.getAch().getId() + " " + ChatColor.GOLD + a.getAch().getName() + " - " + ChatColor.WHITE + a.getDate().toString());
//                }
//                String msg = BeardAch.getConfig().getString("ach.msg.ach", null);
//
//                if (msg != null) {
//                    player.sendMessage(msg);
//                }
//
//            } else if (args.length > 0) {
//                StringBuilder ach = new StringBuilder();
//                for(String s : args) {
//                    ach.append(s + " ");
//                }
//                String searchString = ach.toString().trim();
//                Achievement a = null;
//                Iterator<Achievement> it = BeardAch.getAchievementManager().getLoadedAchievements().iterator();
//                while(it.hasNext()){
//                    a = it.next();
//                    if(a.getName().equalsIgnoreCase(searchString)){
//                        it = null;
//                        break;
//                    }
//                }
//                if (a != null) {
//                    player.sendMessage(ChatColor.GOLD + a.getName());
//                    player.sendMessage(ChatColor.BLUE + a.getDescrip());
//
//                    // if they have unlocked it, tell them when they did
//                    for (AchievementPlayerLink aLink : BeardAch.getAchievementManager().getAchievements(player.getUniqueId())) {
//                        if (aLink.getSlug().equals(a.getSlug())) {
//                            player.sendMessage(ChatColor.WHITE + "You Unlocked this: " + aLink.getDate().toString());
//                        }
//                    }
//
//                }
//                else
//                {
//                    player.sendMessage("No achievement found with that name");
//                }
//            }
//
//        }
//
//        return true;
//    }
//
//    @Override
//    public List<String> onTabComplete(CommandSender sender, Command cmd, String cmdlbl, String[] args) {
//        StringBuilder ach = new StringBuilder();
//        for(String s : args) {
//            ach.append(s + " ");
//        }
//        List<String> l = new ArrayList<String>();
//        for(Achievement a : BeardAch.getAchievementManager().getAchievementsList()){
//            if(a.getName().contains(ach.toString().trim())){l.add(a.getName());}
//        }
//        return l;
//    }
//
//}
