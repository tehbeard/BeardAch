package me.tehbeard.BeardAch.achievement.rewards.command;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

@Configurable(tag="playercommand",name="Execute command as player")
public class PlayerCommandReward implements IReward {

    @Expose
    @EditorField(alias="Command")
    String comm = "";
    public void configure(Achievement ach, String config) {
        comm = config;
        
    }

    public void giveReward(Player player) {
        
        Bukkit.dispatchCommand(player, comm);
        
    }

    public void configure(Achievement ach) {
        
    }

}
