package com.tehbeard.beardach.achievement.triggers.player;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;

/**
 * Checks if a player is an OP
 * 
 * @author James
 * 
 */
@ComponentDescription(description = "Is the player a vanilla OP",categories ={"player"})
@Configurable(tag = "isop", name = "is player an OP")
public class IsOpTrigger implements ITrigger {

    @ComponentFieldDescription(value = "Is the player a vanilla Operator")
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
