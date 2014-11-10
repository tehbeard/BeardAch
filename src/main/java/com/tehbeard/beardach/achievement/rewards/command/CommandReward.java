package com.tehbeard.beardach.achievement.rewards.command;

import org.bukkit.Bukkit;
import org.spongepowered.api.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

@ComponentHelpDescription(description = "Execute a command as the console")
@Configurable(tag = "comm", name = "Execute console command")
public class CommandReward implements IReward {

    @ComponentValueDescription(value = "Command to execute, <PLAYER> is replaced with the player's name")
    @Expose
    @EditorField(alias = "Command")
    String command = "";

    @Override
    public void giveReward(Player player) {

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("<PLAYER>", player.getName()));
    }

    @Override
    public void configure(Achievement ach) {

    }

}
