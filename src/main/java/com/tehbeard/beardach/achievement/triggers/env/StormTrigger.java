package com.tehbeard.beardach.achievement.triggers.env;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

@ComponentHelpDescription(description = "Triggers if world a player in is stormy")
@Configurable(tag = "storm", name = "Is it stormy?")
public class StormTrigger implements ITrigger {

    @ComponentValueDescription(description = "Set true for should have a storm, false for should be clear")
    @Expose
    @EditorField(alias = "Storm currently?", type = EditorFieldType.bool)
    private boolean isStormy;

    @Override
    public void configure(Achievement arg0, String config) {
        if (config.equalsIgnoreCase("true")) {
            isStormy = true;
        }

        if (config.equalsIgnoreCase("false")) {
            isStormy = false;
        }
        throw new IllegalArgumentException("invalid value for storm");

    }

    @Override
    public boolean checkAchievement(Player p) {

        return p.getWorld().hasStorm() == isStormy;
    }

    @Override
    public void configure(Achievement ach) {
    }

}
