package com.tehbeard.beardach.achievement.triggers.player;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;

/**
 * Checks if a player has a permission node
 * 
 * @author James
 * 
 */
@ComponentDescription(description = "Does the player have a permission node",categories ={"player"})
@Configurable(tag = "perm", name = "has permission node")
public class PermCheckTrigger implements ITrigger {

    @ComponentFieldDescription(value = "Permission node to check, does not have to one declared by a plugin")
    @Expose
    @EditorField(alias = "permission node")
    String perm;

    @Override
    public boolean checkAchievement(Player player) {
        // if player has stat
        return player.hasPermission(perm);
    }

    @Override
    public void configure(Achievement ach) {

    }

}
