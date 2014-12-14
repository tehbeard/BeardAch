package com.tehbeard.beardach.achievement.triggers.player;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;

@ComponentDescription(description = "Checks the players bank balance", dependencies = { "Vault"},categories ="player")
@Configurable(tag = "bankbalance", name = "(Vault)Check bank balance")
public class EconomyTrigger implements ITrigger {

    @ComponentFieldDescription(value = "Balance must be atleast this value to trigger, supports decimal values")
    @Expose
    @EditorField(alias = "Lower threshold")
    private double amount;



    @Override
    public boolean checkAchievement(Player player) {
        return false;
    }

    @Override
    public void configure(Achievement ach) {

    }

}
