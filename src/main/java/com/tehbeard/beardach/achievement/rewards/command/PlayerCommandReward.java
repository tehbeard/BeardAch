package com.tehbeard.beardach.achievement.rewards.command;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.dataSource.configurable.Configurable;
import com.tehbeard.beardach.dataSource.json.editor.EditorField;

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
