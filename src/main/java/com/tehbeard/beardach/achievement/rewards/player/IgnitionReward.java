package com.tehbeard.beardach.achievement.rewards.player;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import org.spongepowered.api.data.manipulator.entity.IgniteableData;

@ComponentDescription(description = "Sets a player on fire")
@Configurable(tag = "ignite", name = "Ignite the player")
public class IgnitionReward implements IReward {

    @Expose
    @EditorField(alias = "Ignite for (ticks)")
    int ticks;

    @Override
    public void giveReward(Player player) {
        player.getOrCreate(IgniteableData.class).get().setFireTicks(ticks);
    }

    @Override
    public void configure(Achievement ach) {
    }

}
