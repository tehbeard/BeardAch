package com.tehbeard.beardach.achievement.rewards.command;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.FakeConsole;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.spongepowered.api.util.command.CommandException;

@ComponentHelpDescription(description = "Execute a command as the console")
@Configurable(tag = "comm", name = "Execute console command")
public class CommandReward implements IReward {

    @ComponentValueDescription(value = "Command to execute, <PLAYER> is replaced with the player's name")
    @Expose
    @EditorField(alias = "Command")
    String command = "";

    @Override
    public void giveReward(Player player) {
        try {
            BeardAch.getGame().getCommandDispatcher().call(new FakeConsole(), command.replace("<PLAYER>", player.getName()), null);
        } catch (CommandException ex) {
            BeardAch.getLogger().error("CommandReward",ex);
        }
    }

    @Override
    public void configure(Achievement ach) {
    }

}
