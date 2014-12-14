package com.tehbeard.beardach.achievement.rewards.player;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;

@ComponentDescription(description = "Sets the players health to certain value")
@Configurable(tag = "sethealth", name = "Heal player")
public class SetHealthReward implements IReward {

    @Expose
    @EditorField(alias = "Health to add")
    int health;

    @Override
    public void giveReward(Player player) {
        player.setHealth(health);
    }

    @Override
    public void configure(Achievement ach) {
    }

}
