package com.tehbeard.beardach.achievement.rewards.player;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import org.spongepowered.api.world.Location;

@ComponentDescription(description = "Teleports a player to a particular location")
@Configurable(tag = "teleport", name = "Teleport to location")
public class TeleportReward implements IReward {

    @Expose
    @EditorField(alias = "Teleport to", type = EditorFieldType.location)
    private Location to;

    @Override
    public void giveReward(Player player) {
        player.teleport(to);

    }

    @Override
    public void configure(Achievement ach) {

    }

}
