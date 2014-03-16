package com.tehbeard.beardach.achievement.triggers.player;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

/**
 * Checks if a player is an OP
 * 
 * @author James
 * 
 */
@ComponentHelpDescription(description = "Is the player a vanilla OP")
@Configurable(tag = "isop", name = "is player an OP")
public class IsOpTrigger implements ITrigger {

    @ComponentValueDescription(value = "Is the player a vanilla Operator")
    @Expose
    @EditorField(alias = "is OP",type=EditorFieldType.bool)
    boolean isop;

    @Override
    public boolean checkAchievement(Player player) {
        // if player has stat
        return player.isOp() == isop;
    }

    @Override
    public void configure(Achievement ach) {

    }

}
