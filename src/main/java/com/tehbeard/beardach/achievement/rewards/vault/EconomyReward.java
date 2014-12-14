package com.tehbeard.beardach.achievement.rewards.vault;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import org.spongepowered.api.entity.player.Player;

@ComponentDescription(description = "Gives a player some money", dependencies = { "Vault"})
@Configurable(tag = "money", name = "(vault) Give money")
public class EconomyReward implements IReward {

    @Expose
    @EditorField(alias = "Amount to give (can be a decimal")
    private double amount = 0.0D;

    @Override
    public void giveReward(Player player) {
        
    }

    @Override
    public void configure(Achievement ach) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }

}
