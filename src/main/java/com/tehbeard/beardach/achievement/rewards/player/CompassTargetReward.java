package com.tehbeard.beardach.achievement.rewards.player;

import org.bukkit.Location;
import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;

@ComponentHelpDescription(description = "Sets the player's compass target to new location")
@Configurable(tag = "compass", name = "set compass target")
public class CompassTargetReward implements IReward {

    @Expose
    @EditorField(alias = "Location", type = EditorFieldType.location)
    private Location l;

    @Override
    public void giveReward(Player player) {
        player.setCompassTarget(l);

    }

    @Override
    public void configure(Achievement ach) {
    }

}
