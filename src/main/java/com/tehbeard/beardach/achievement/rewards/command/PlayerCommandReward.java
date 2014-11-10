package com.tehbeard.beardach.achievement.rewards.command;

import org.bukkit.Bukkit;
import org.spongepowered.api.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;

@Configurable(tag = "playercommand", name = "Execute command as player")
public class PlayerCommandReward implements IReward {

    @Expose
    @EditorField(alias = "Command")
    String comm = "";

    @Override
    public void giveReward(Player player) {

        Bukkit.dispatchCommand(player, comm);

    }

    @Override
    public void configure(Achievement ach) {

    }

}
