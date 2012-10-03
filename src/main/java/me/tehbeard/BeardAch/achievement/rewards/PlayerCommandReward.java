package me.tehbeard.BeardAch.achievement.rewards;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.configurable.Usage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Configurable(tag="playercommand")
@Usage(arguments="command|makes player execute a command, replaces <PLAYER> with playername",packageName="base")
public class PlayerCommandReward implements IReward {

    String comm = "";
    public void configure(Achievement ach, String config) {
        comm = config;
        
    }

    public void giveReward(Player player) {
        
        Bukkit.dispatchCommand(player, comm);
        
    }

}
