package me.tehbeard.BeardAch.achievement.rewards;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.*;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

@Configurable(tag="playercommand")
@Usage(arguments=@Argument(name="command",desc="command to execute as the player"),packageName="base")
public class PlayerCommandReward implements IReward {

    @Expose
    String comm = "";
    public void configure(Achievement ach, String config) {
        comm = config;
        
    }

    public void giveReward(Player player) {
        
        Bukkit.dispatchCommand(player, comm);
        
    }

    public void configure(Achievement ach) {
        // TODO Auto-generated method stub
        
    }

}
