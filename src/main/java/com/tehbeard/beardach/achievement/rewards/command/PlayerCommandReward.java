package com.tehbeard.beardach.achievement.rewards.command;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.spongepowered.api.util.command.CommandException;

@Configurable(tag = "playercommand", name = "Execute command as player")
public class PlayerCommandReward implements IReward {

    @Expose
    @EditorField(alias = "Command")
    String comm = "";

    @Override
    public void giveReward(Player player) {
        try {
            BeardAch.getGame().getCommandDispatcher().call(player, comm.replace("<PLAYER>", player.getName()),null);
        } catch (CommandException ex) {
            BeardAch.getLogger().error("PlayerCommandReward",ex);
        }
    }

    @Override
    public void configure(Achievement ach) {

    }

}
