package me.tehbeard.BeardAch.achievement.rewards;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Configurable(tag="playercommand")
public class PlayerCommandReward implements IReward {

    String comm = "";
    public void configure(Achievement ach, String config) {
        comm = config;
        
    }

    public void giveReward(Player player) {
        
        Bukkit.dispatchCommand(player, comm);
        
    }

}
