package com.tehbeard.beardach.achievement.triggers.env;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;
import org.spongepowered.api.world.weather.Weathers;

@ComponentDescription(description = "Triggers if world a player in is stormy",categories = "enviromental")
@Configurable(tag = "storm", name = "Is it stormy?")
public class StormTrigger implements ITrigger {

    @ComponentFieldDescription(value = "Set true for should have a storm, false for should be clear")
    @Expose
    @EditorField(alias = "Storm currently?", type = EditorFieldType.bool)
    private boolean isStormy;

    @Override
    public boolean checkAchievement(Player p) {

        return p.getWorld().getWeather() != Weathers.CLEAR;
    }

    @Override
    public void configure(Achievement ach) {
    }

}
